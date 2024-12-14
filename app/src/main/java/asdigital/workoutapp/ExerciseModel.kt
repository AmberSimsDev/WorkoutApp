package asdigital.workoutapp

class ExerciseModel (
    private var id: Int,
    private var name: String,
    private var image: Int,
    private var isCompleted: Boolean,
    private var isSelected: Boolean
){
// Create a getter for id
    fun getId():Int{
        return id
    }
// Create a setter for id
    fun setId(id: Int){
        this.id = id
    }
// Create another getter for name
    fun getName():String {
        return name
    }
//Create setter for name
    fun setName(name:String){
        this.name = name
    }
//Create getter for image
    fun getImage() : Int{
        return image
    }
//Create setter for image
    fun setImage(image: Int){
        this.image = image
    }
//Create getter & setter for isCompleted
    fun getIsCompleted() : Boolean{
        return isCompleted
    }
    fun setIsCompleted(isCompleted: Boolean){
        this.isCompleted = isCompleted
    }
//Create getter & setter  for isSelected
    fun getIsSelected(): Boolean{
        return isSelected
    }
    fun setIsSelected(isCompleted: Boolean){
        this.isSelected = isSelected
    }


}