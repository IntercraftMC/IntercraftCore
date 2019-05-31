
NAME="w"

MATCHERS=("ingot" "nugget" "dust" "dustsmall" "plate")

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
