#!/usr/bin/env bash

NAME="fec"

MATCHERS=("ingot" "nugget" "dust" "dustsmall" "plate" "gear" "rod")
#MATCHERS=("al" "cu" "au" "ir" "fe" "pb" "li" "hg" "ag" "th" "sn" "ti" "w" "u" "zn" "cuzn" "cusn" "fec")

for VAR in ${MATCHERS[*]}
do
    STRUCTURE="\
    {
      \"parent\": \"item/generated\",
      \"textures\": {
        \"layer0\": \"intercraftcore:item/ore_chunks/base_type1\",
        \"layer1\": \"intercraftcore:item/ore_chunks/pieces_type1_1\"
      }
    }
    "
    echo "Creating file "$NAME"_"$VAR".json"
    echo $STRUCTURE > $NAME"_"$VAR".json"
done
