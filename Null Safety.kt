var b: String? = "abc"
b = null // ok
print(b)


var b: String? = null
if (b != null && b.isNotEmpty()) {
    print(b)
}

if (b?.isNotEmpty() == true){
  print(b)
}

bob?.department?.head?.name

val listWithNulls: List<String?> = listOf("Kotlin", null)
for (item in listWithNulls) {
    item?.let { println(it) } // prints A and ignores null
}


//elvis
val l = b?.length ?: -1

//early return
myVar ?: return

//for NPE-lovers
val l = b!!.length
