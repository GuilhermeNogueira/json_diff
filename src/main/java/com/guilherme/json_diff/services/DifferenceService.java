package com.guilherme.json_diff.services;

import com.guilherme.json_diff.dto.DifferenceResultDTO;
import com.guilherme.json_diff.exceptions.DiffException;
import com.guilherme.json_diff.models.DiffFileType;
import com.guilherme.json_diff.models.Difference;
import com.guilherme.json_diff.repositories.DifferenceRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.guilherme.json_diff.models.Difference.Side;

@Service
@Log
public class DifferenceService {

    @Autowired
    private DifferenceRepository repository;


    public void newDiff(Long id, String file, Side side, DiffFileType type) {

        if (!type.validate(file)) {
            throw new DiffException(400, String.format("Given file is not valid for type %s", type.toString()));
        }
        //Get or create new instance
        Difference diff = repository.getByKeyIdAndKeySide(id, side)
                .orElse(Difference.builder()
                        .id(id)
                        .side(side)
                        .file(file)
                        .type(type)
                        .build()
                );

        log.info("Storing difference in db");
        repository.save(diff);
    }

    /**
     * Start diff process.
     * Calls file's comparator (based on it's type) with decoded values;
     */
    public DifferenceResultDTO diff(Long id) {
        Map<Side, Difference> differenceMap = prepareDiff(id);

        Difference right = differenceMap.get(Side.RIGHT);
        Difference left = differenceMap.get(Side.LEFT);

        //since both are the same type, we are going to use any side to get type
        DiffFileType fileType = right.getFileType();

        return fileType
                .getComparator()
                .getDifference(
                        fileType.decode(right.getFile()),
                        fileType.decode(left.getFile())
                );
    }


    /**
     * Based on ID, find a pair of difference and do validations:
     * - Id Exists.
     * - Both sides (RIGHT AND LEFT) are present.
     *
     * @return A Map with difference.SIDE as key and difference as value
     */
    private Map<Side, Difference> prepareDiff(Long id) {
        List<Difference> results = repository.getByKeyId(id);

        if (results.size() == 0) {
            throw new DiffException(404, String.format("diffId %s does not exists.", id.toString()));
        }

        if (results.size() != 2) {
            /* Finding which side is missing. */
            Side missingSide = results.stream()
                    .findFirst()
                    .map(r -> r.getKey().getSide() == Side.LEFT ? Side.LEFT : Side.RIGHT)
                    .get();

            throw new DiffException(400, String.format("Not possible to diff, missing %s side", missingSide.toString()));
        }

        return results.stream()
                .collect(Collectors.toMap(d -> d.getKey().getSide(), Function.identity()));
    }
}
