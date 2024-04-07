override fun onCreate(savedInstanceState: Bundle?) {
   super.onCreate(savedInstanceState)
   /**
    * Flow of [DevicePosture] that emits every time there's a change in the windowLayoutInfo
    */
   val devicePostureFlow =  WindowInfoTracker.getOrCreate(this).windowLayoutInfo(this)
       .flowWithLifecycle(this.lifecycle)
       .map { layoutInfo ->
           val foldingFeature =
               layoutInfo.displayFeatures
                   .filterIsInstance<FoldingFeature>()
                   .firstOrNull()
           when {
               isBookPosture(foldingFeature) ->
                   DevicePosture.BookPosture(foldingFeature.bounds)

               isSeparating(foldingFeature) ->
                   DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)

               else -> DevicePosture.NormalPosture
           }
       }
       .stateIn(
           scope = lifecycleScope,
           started = SharingStarted.Eagerly,
           initialValue = DevicePosture.NormalPosture
       )
   
   
    setContent {
       // App compose content
   }
}
