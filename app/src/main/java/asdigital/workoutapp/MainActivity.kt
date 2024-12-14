package asdigital.workoutapp
//NOTES FROM LESSON
//VIEW BINDING  IS GOOD BECAUSE 1) IMPLEMENTATION IS SHORT AND CONCISE
//2) FASTER & EFFICIENT COMPILE TIME 3) SAFELY ENSURES VIEWS IN CONNECTED LAYOUT ARE REFFERENCED
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import asdigital.workoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var xmlView: ActivityMainBinding?  = null
    // here we are giving the mainActivity xml to binding and were gonna access everything from it directly
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        xmlView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(xmlView?.root)

       // val flStartButton : FrameLayout = findViewById(R.id.flStart)
        xmlView?.flStart?.setOnClickListener {
        //we are accessing MAXML using binding , then getting flStart frame layout
        //before we had : flStartButton.setOnClickListener{
           /* Toast.makeText(
                this@MainActivity,
                "Here we will start the exercise.",
                Toast.LENGTH_SHORT
            ).show()*/
     // Here we are going to create a means of moving over to another screen so need an intent
            //Here we are going to create an intent class so we can navigate to another screen
         var intent = Intent(this,ExerciseActivity::class.java)
     //first, create class called intent, add context using "this", then put the class to move over to
         startActivity(intent)
        }
    }

// WHEN USING VIEW BINDING IN YOUR PROJECT MAKE SURE TO USE
//THE ON DESTROY METHOD WHERE YOU SET BINDING BACK TO NULL
    override fun onDestroy() {
    super.onDestroy()

    xmlView = null
    }
}