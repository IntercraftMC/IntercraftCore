#!/usr/bin/env bash

NAME="w"

MATCHERS=("ingot" "nugget" "dust" "dustsmall" "plate")
#MATCHERS=("al" "cu" "au" "ir" "fe" "pb" "li" "hg" "ag" "th" "sn" "ti" "w" "u" "zn" "cuzn" "cusn" "fec")

for VAR in ${MATCHERS[*]}
do
    STRUCTURE="\
    {
      \"parent\": \"item/generated\",
      \"textures\": {
        \"layer0\": \"intercraftcore:item/$VAR\"
      }
    }
    "
    echo "Creating file "$NAME"_"$VAR".json"
    echo $STRUCTURE > $NAME"_"$VAR".json"
done
