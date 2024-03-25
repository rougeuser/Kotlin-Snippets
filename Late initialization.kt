class MyActivity : AppCompatActivity() {
  // non-null, but not initalized
  lateinit var recyclerView: RecyclerView

  override fun onCreate(savedInstanceState: Bundle?) {
    // …
    // initialized here
    recyclerView = findViewById(R.id.recycler_view)
  }
}
