    #!/usr/bin/env bash

TYPE="ingot"

NAMES=("copper" "lead" "silver" "tin" "tungsten" "uranium")

MATCHERS=("cu" "pb" "ag" "sn" "w" "u")

for i in $(seq 0 ${#NAMES});
do

    STRUCTURE="\
    {
      \"replace\": false,
      \"values\": [
        \"intercraftcore:"${MATCHERS[$i]}"_"$TYPE"\"
      ]
    }
    "
    echo "Creating file "${NAMES[$i]}".json"
    echo $STRUCTURE > ${NAMES[$i]}".json"
done
