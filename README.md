# json_diff

Questions:
- The requirement: "If not of equal size just return that" That what? The results? or that those files are not equals?
- I should provide all data? equals, sameSize and the diffs always? Or each one is a different kind of response?
- The size of the file: It should de the re

The results shall provide the following info in JSON format
o If equal return that
o If not of equal size just return that
o If of same size provide insight in where the diffs are, actual diffs are not needed.
§ So mainly offsets + length in the data


Assumptions:
- We can have diffs without left or right side. This case I'm going to add a new validation during diff
- We are going to receive the ID during diff creation, if we have an already registered id, we are going to override 


Suggestions:
- Validate JSON file