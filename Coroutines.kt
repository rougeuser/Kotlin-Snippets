GlobalScope.launch {
    delay(2000) // Suspend function waits until completed
    log("one")
    delay(1000)
    log("two")
}
log("three")

/*
Output will be
0ms >    three
2000ms > one
3000ms > two
*/
