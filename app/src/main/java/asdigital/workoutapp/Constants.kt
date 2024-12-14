package asdigital.workoutapp
//The function below will give you access to the default exercise list
object Constants {

    fun defaultExerciseList():ArrayList<ExerciseModel>{
       val exerciseList = ArrayList<ExerciseModel>()
       val jumpingJacks = ExerciseModel(
           1,
           "Jumping Jacks",
           R.drawable.ic_jumping_jacks,
           false,
           false,
       )
        //Now add jumpingJacks exercise to exercise list in order to return it
        exerciseList.add(jumpingJacks)
    //Now add remaining exercises
        val abdominalCrunch = ExerciseModel(
            2,
            "Abdominal Crunch",
            R.drawable.ic_abdominal_crunch,
            false,
            false,
        )
        val highKnees = ExerciseModel(
            3,
            "High Knees",
            R.drawable.ic_high_knees_running_in_place,
            false,
            false,
        )
        val lunge = ExerciseModel(
            4,
            "Lunge",
            R.drawable.ic_lunge,
            false,
            false,
        )
        val planks = ExerciseModel(
            5,
            "Planks ",
            R.drawable.ic_plank,
            false,
            false,
        )
        val pushUp = ExerciseModel(
            6,
            "Push Ups",
            R.drawable.ic_push_up,
            false,
            false,
        )
        val pushUpAndRotation = ExerciseModel(
            7,
            "Push Up & Rotation",
            R.drawable.ic_push_up_and_rotation,
            false,
            false,
        )
        val sidePlank = ExerciseModel(
            8,
            "Side Planks",
            R.drawable.ic_side_plank,
            false,
            false,
        )
        val squats = ExerciseModel(
            9,
            "Squats",
            R.drawable.ic_squat,
            false,
            false,
        )
        val stepUpOntoChair = ExerciseModel(
            10,
            "Step Ups with Chair",
            R.drawable.ic_step_up_onto_chair,
            false,
            false,
        )
        val tricepsDip = ExerciseModel(
            11,
            "Tricep Dips",
            R.drawable.ic_triceps_dip_on_chair,
            false,
            false,
        )
        val wallSit = ExerciseModel(
            12,
            "Wall Sits",
            R.drawable.ic_wall_sit,
            false,
            false,
        )

        return exerciseList
    }
}