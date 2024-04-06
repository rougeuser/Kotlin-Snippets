override fun onCreate(savedInstanceState: Bundle?) {
   super.onCreate(savedInstanceState)

   setContent {
       ReplyTheme {
           val uiState = viewModel.uiState.collectAsState().value
           ReplyApp(uiState)
       }
   }
}
