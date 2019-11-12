
public class DynamicArray extends commonArrayBlueprint implements DynamicArrayBehavior{
  private int array[];
  
  public DynamicArray(){ 
        this.array = new int[1]; 
        super.setNumElements(0); 
        super.setNumAllocations(1);
  }
  
  @Override
  public void storeAt(int item, int index){
    if (index >=0 && index < super.getNumElements()){
      this.array[index] = item;
    }else{
      System.out.println("DynamicArray:storeAt(): The index is bigger than the current length of the array. Modification can not be performed.");
    }
  } 

  @Override
  public int getFrom(int index){
    if (index >= 0 && index < super.getNumElements()){
      return this.array[index];
    }
    System.out.println("DynamicArray:getFrom(): The index is bigger than the current length of the array. Item can not be retrieved.");
    return -1; // what is wrong with this "-1" approach? Solution: Try-catch (future topic)
  }
  
  private void expandArray(){
   int newSize = 2 * super.getNumAllocations();
   int tempArray[] = new int[newSize];
   // now copy from the original array
   for (int i = 0; i < super.getNumElements(); i++){
     tempArray[i] = this.array[i];
   }
   this.array = tempArray;
   super.setNumAllocations(newSize);
  }
  
  @Override
  public void append(int item){
    if (super.getNumElements() == super.getNumAllocations()){
      this.expandArray();
    }
    this.array[super.getNumElements()] = item;
    super.increaseNumElement();
  }
  
  @Override
  public int getFirstItem(){
    if (super.getNumElements() > 0){
     return this.array[0];
    }
    System.out.println("DynamicArray:getFirstItem(): There is no item!");
    return -1; // what is wrong with this "-1" approach? Solution: Try-catch (future topic)
  }
  
  @Override
  public int getLastItem(){
    if (super.getNumElements() > 0){
     return this.array[super.getNumElements()-1];
    }
    System.out.println("DynamicArray:getFirstItem(): There is no item!");
    return -1; // what is wrong with this "-1" approach? Solution: Try-catch (future topic)
  }
  
  @Override
  public String toString(){
    String output = "[";
    for (int i = 0; i < super.getNumElements(); i++){
      output += Integer.toString(this.array[i]);
      if (i < super.getNumElements() - 1){
        output += ", ";
      }
    }
    output += "]";
    return output;
  }
  
  private void srinkArray(){
    if (super.getNumElements() < ((int)(super.getNumAllocations()/2))){
      int tempArray[] = new int[(int)(super.getNumAllocations()/2)];
      for (int i = 0; i < super.getNumElements(); i++){
        tempArray[i] = this.array[i];
      }
      this.array = tempArray;
      int newAllocSize = (int)(super.getNumAllocations()/2);
      super.setNumAllocations(newAllocSize); // if we do not decrease, our code will be in error state
    }
  }
  
  @Override
  public void remove(){
    if (super.getNumElements() > 0){
      this.array[super.getNumElements()-1] = 0; // why 0? what default value java assigns to an int array when created?
      super.decreaseNumElement(); // if we do not decrease, our code will be in error state
      this.srinkArray(); // optional, you can skip this part if you want.
    }else{
      System.out.println("DynamicArray:remove(): There is no item to remove.");
    }
  }
  
  @Override
  public void removeAt(int index){
    if (super.getNumElements() > 0){
      if (index >= 0 && index < super.getNumElements()){
        this.array[index] = 0; // why? We are gonna take that 0 to the last. Because we want to maintain contiguity of the element's index. No unwanted 0's in the middle.
        for (int i = index + 1; i < super.getNumElements(); i++){
          // the next three lines simply swaps pairs of items
          int temp = this.array[i-1];
          this.array[i-1] = this.array[i];
          this.array[i] = temp;
        }
        super.decreaseNumElement(); // if we do not decrease, our code will be in error state
        this.srinkArray(); // optional, you can skip this part if you want.
      }else{
        System.out.println("DynamicArray:removeAt(): Invalid index. Please provide a valid index.");
      }
    }else{
      System.out.println("DynamicArray:removeAt(): There is no item to remove.");
    }
  }
  
  @Override
  public int len(){
    return super.getNumElements();
  }
  
  public void test(){
   System.out.println("total elements: " + Integer.toString(super.getNumElements())  + "; total allocation: " + Integer.toString(super.getNumAllocations()) + "\n"); 
  }
  
  public static void main(String args[]){
    DynamicArray da = new DynamicArray();
    
    System.out.println(da);
    da.test();
    
    da.append(32);
    System.out.println(da);
    da.test();
    
    da.append(23);
    System.out.println(da);
    da.test();
    
    da.append(443);
    System.out.println(da);
    da.test();
    
    da.remove();
    System.out.println(da);
    da.test();
    
    da.removeAt(1);
    System.out.println(da);
    da.test();
    
    System.out.println(da);
    System.out.println(da.len());
  }

}