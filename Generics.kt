class ApiResponse<T>(t:T){
    var value = t
}

val r = ApiResponse<String>("Data")
val r = ApiResponse<User>(User("name",20))
