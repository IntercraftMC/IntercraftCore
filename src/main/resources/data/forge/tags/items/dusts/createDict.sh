#!/usr/bin/env bash

TYPE="dust"

#"silver" "aluminium" "gold" "copper" "iron" "iridium" "lead" "tin" "thorium" "uranium" "tungsten" "zinc"
#NAMES=("aluminium" "copper" "gold" "iridium" "iron" "lead" "silver" "thorium" "tin" "tungsten" "uranium" "zinc")
NAMES=("aluminium" "copper" "iridium" "lead" "lithium" "silver" "thorium" "tin" "titanium" "tungsten" "uranium" "zinc" "carbon" "silicon" "brass" "bronze" "electrum" "steel")
MATCHERS=("al" "cu" "ir" "pb" "li" "ag" "th" "sn" "ti" "w" "u" "zn" "c" "si" "cuzn" "cusn" "agau" "fec")

for ((i=0;i<${#NAMES};i++));
do

    STRUCTURE="\
    {
      \"replace\": false,
      \"values\": [
        \"intercraftcore:"${MATCHERS[$i]}"_"$TYPE"\"
      ]
    }"
    echo "Creating file "${NAMES[$i]}".json"
    echo $STRUCTURE > ${NAMES[$i]}".json"
done
