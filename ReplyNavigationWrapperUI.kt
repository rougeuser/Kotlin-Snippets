@Composable
private fun ReplyNavigationWrapperUI(
   navigationType: ReplyNavigationType,
   contentType: ReplyContentType,
   replyHomeUIState: ReplyHomeUIState
) {
   // App drawer state

   if (navigationType == ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER) {
       PermanentNavigationDrawer(drawerContent = {...}) {
           ReplyAppContent(navigationType, contentType, replyHomeUIState)
       }
   } else {
       ModalNavigationDrawer(
           drawerContent = {...},
           drawerState = drawerState
       ) {
           ReplyAppContent(
               navigationType, contentType, replyHomeUIState,
               onDrawerClicked = {...}
           )
       }
   }

}



@Composable
fun ReplyAppContent(
   navigationType: ReplyNavigationType,
   contentType: ReplyContentType,
   replyHomeUIState: ReplyHomeUIState,
   onDrawerClicked: () -> Unit = {}
)
