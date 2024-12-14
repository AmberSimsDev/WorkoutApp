package asdigital.workoutapp

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import asdigital.workoutapp.databinding.ActivityExerciseBinding
import androidx.appcompat.widget.Toolbar
import java.util.Locale


class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding: ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null // How much time to rwst
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null // How much time to rwst
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    private var tts: TextToSpeech? = null //make sure to initialise in onCreate
    private var player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Todo 2 inflate the layout
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        //Todo 3 pass in binding?.root in the content view
        setContentView(binding?.root)

        //Todo 4: then set support action bar and get toolBarExcercise using the binding variable
        setSupportActionBar(binding?.toolbarExercise as? Toolbar) //To create back button


            supportActionBar?.setDisplayHomeAsUpEnabled(true)

        exerciseList = Constants.defaultExerciseList()
        //Initialise tts here
        //We initialise  the TextToSpeech class and assign it to the tts variable
        tts = TextToSpeech(this, this)

        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }
        setUpRestView() //Also calls setRestProgressBar
    }

    // Make sure timer is reset when leaving and returning to screen
    private fun setUpRestView() {

        try{
            val soundURI = Uri.parse("android.resource://asdigital.workoutapp/"
                    + R.raw.app_src_main_res_raw_press_start)
            player= MediaPlayer.create(applicationContext, soundURI)
            player?.isLooping = false
            player?.start() //use start method to play
        }catch (e:Exception){
            e.printStackTrace()
        }
        //Everything visible below must be invisible during setup view
        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        binding?.tvUpcomingLabel?.visibility = View.VISIBLE
        binding?.tvUpComingExerciseName?.visibility = View.VISIBLE

        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        binding?.tvUpComingExerciseName?.text =
            exerciseList!![currentExercisePosition + 1].getName()
        setRestProgressBar()
    }

    private fun setUpExerciseView() {
        //Everything visible below must be invisible during rest view
        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        binding?.tvUpcomingLabel?.visibility = View.INVISIBLE
        binding?.tvUpComingExerciseName?.visibility = View.INVISIBLE

        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        speakOut(exerciseList!![currentExercisePosition].getName())

        //After displaying the images, its time to set them
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text = exerciseList!![currentExercisePosition].getName()
        setExerciseProgressBar()
    }

    private fun setRestProgressBar() {
        binding?.progressBar?.progress = restProgress

        restTimer = object : CountDownTimer(10000, 1000) {
            //after every 1000 miliseconds call this code
            override fun onTick(p0: Long) {
                restProgress++ // increases by 1 after we display it
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }

            // once you,re done call this on finish code
            override fun onFinish() {
                currentExercisePosition++ // once done we can go to next exercise from arraylist
                setUpExerciseView()
                /*Toast.makeText(
                    this@ExerciseActivity, "Here now we will start the exercise",
                    Toast.LENGTH_SHORT).show()*/

            }

        }.start()

    }

    private fun setExerciseProgressBar() {
        binding?.progressBarExercise?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(10000, 1000) {
            //after every 1000 miliseconds call this code
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++ // increases by 1 after we display it
                binding?.progressBarExercise?.progress = 30 - exerciseProgress
                binding?.tvTimerExercise?.text = (30 - exerciseProgress).toString()
            }

            // once you,re done call this on finish code
            override fun onFinish() {
                if (currentExercisePosition < exerciseList?.size!! - 1) {
                    //If we have exercises left, lets set a rest view
                    setUpRestView()
                } else {
                    Toast.makeText(
                        this@ExerciseActivity,
                        "Congrats! You have completed the 7 minutes workout.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }.start()

    }

    //REMEMBER IF YOU USE BINDING (AS WELL AS A TIMER) YOU MUST USE ON-DESTROY METHOD
    override fun onDestroy() {
        super.onDestroy()

        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        //Shutting down tts feature when activity is destroyed
        //Start
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        if(player !=null){
            player!!.stop()
        }
        binding = null
    }

    override fun onInit(status: Int) {
        //First check status then set language
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
            //what to do if something goes wrong
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED
            )
            //Report an error to the user
                Log.e("TTS", "The Language specified is not supported!")
        } else
            Log.e("TTS", "Initialization Failed!")
    }


    //Add text to Speech Function here
    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}


