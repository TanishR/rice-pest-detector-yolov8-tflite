
### Layout Files:
1. activity_main (Used to open the live detection camera)

   1. Displays the live camera for detection (PreviewView)
   2. Draws the bounding boxes (OverlayView)
   3. Displays the inference time (TextView)

2. activity_main_drawer (Consists the main layout of the app with sidebar)

   1. Consists the side drawer (DrawerLayout)
   2. Shows the title bar and the hamburger button (Toolbar)
   3. Consists the Home and Pests options (NavigationView)

3. activity_welcome (Welcome screen UI) 

   1. App opens with an image (ImageView)
   2. Also contains the Live Detection Button (Button - opens MainActivity)
   
4. fragment_pest_list (Displays the pest cards 2 per row)
   
   1. Used in the PestListFragment
   2. Displays all cards (RecyclerView)

