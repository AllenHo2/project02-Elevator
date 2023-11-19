# Project 2: Elevator

## Abstract
My Elevator project simulates how an elevator decides and picks up passengers. We would then decide on the nearest destination floor out of all of the passengers in the Elevator. Everything in this simulation is timed by a counter called **ticks**.

## Table of Contents

- [Main](#main)
- [Passengers](#passengers)
- [Floors](#floors)
- [Elevators](#elevators)
- [Installation](#installation)
- [Connect](#connect)



## Main
<li>Reads property files</li>
<ul>
  <li>By default, this is the property file: </li>
  
  ```bash
structures = linked
floors = 32
passengers = 0.3
elevators = 1
elevatorCapacity = 10
duration = 500
  ```

</ul>
<li>Runs the Simulation</li>
<li>Keeps track of ticks</li>
<ul>
  <li>Finds the average, longest, and minimum time it takes for a passenger to arrive to their destination after they've been initialized</li>
</ul>
  
## Passengers
<li>Constructs a Passenger object with a passed in starting time & random destination floor</li>
<li>Contains a method to get a random destination for the Passenger object</li>
<li>Implements the Comparable interface to sort Passengers in the Priority Queue when they enter the elevator</li>

```java
public class Passenger implements Comparable<Passenger>{
  public int compareTo(Passenger other) { //compares a passenger to another
       return Integer.compare(this.destFloor, other.destFloor);
    }
}
```

## Floors
<li>Constructs a Floor object in a building</li>
<li>Queues Passenger on the corresponding floor object and passes the time of creation to that Passenger</li>

```java
public void queuePassenger(int time) {
        Random random = new Random();
        if (random.nextFloat() <= probability) {
            Passenger passenger = new Passenger(floorNum, currFloor, time);
            if (passenger.getNearestDestination() > 0) { 
                upQueue.add(passenger);
                System.out.println(passenger + " is added to UpQueue");
            } else if (passenger.getNearestDestination() < 0) {
                downQueue.add(passenger); //else add them to the queue going down
                System.out.println(passenger + " is added to DownQueue");
            }
        }
    }
```

## Elevators
<li>Creates an Elevator object that carries Passengers to its dedicated destination floor</li>
<ul>
  <li>Moves the elevator in 5 floors along its path if there are no passengers in between those 5 floors</li>
  <li>Moves the elevator to a floor where there's a passenger queue if it's within 5 floors</li>
  <li>Finishes its path to the very top or bottom of the building before reversing its path direction</li>
</ul>
<li>Adds passengers if the current floor & path of the elevator matches the Floor's queue direction</li>

```java
 public void addPassengers(int floorNum, Floor[] floor){ //loads an entire queue of passengers from a single floor into the elevator(appropriate heap)
        if(goingUp){ //if going up
            Passenger passenger = floor[currFloor].getUpQueue().peek();
            if(passenger != null) {
                while (upElevator.size() <= capacity && !floor[currFloor].getUpQueue().isEmpty() && passenger.getStartFloor() == currFloor) { //while there is space in the elevator
                    System.out.print("Passenger is added to the up elevator " + passenger + "With a start floor of: " + passenger.getStartFloor() + " and has a destination of " + passenger.getDestFloor() + " ");
                    upElevator.add(floor[currFloor].getUpQueue().poll()); //poll all the passengers from the floor into the elevator going down
                    System.out.println("Floor where passenger is added to the up elevator: " + currFloor);
                }
            }
        } else { //if going down
            Passenger passenger = floor[currFloor].getDownQueue().peek();
            if(passenger != null) {
                while (downElevator.size() < capacity && !floor[currFloor].getDownQueue().isEmpty() && passenger.getStartFloor() == currFloor) { //while there is space in the elevator
                    System.out.print("Passenger is added to the down elevator " + floor[currFloor].getDownQueue().peek() + " ");
                    downElevator.add(floor[currFloor].getDownQueue().poll()); //poll all the passengers from the floor into the elevator going down
                    System.out.println("Floor where passenger is added to the down elevator: " + currFloor);
                }
            }
        }
    }
```

<li>Drops Passengers when the current floor is equal to the destination of Passengers inside the elevator</li>

```java
public int dropPassengers(int time) {
        int timeAtDrop = 0;
        if (goingUp) {
            if (upElevator != null) {
                while (!upElevator.isEmpty() && currFloor == upElevator.peek().getDestFloor()) {
                    timeAtDrop = time - upElevator.peek().getTimeCounter();
                    System.out.println("Passenger with starting floor: " + upElevator.peek().getStartFloor() + " & Destination floor: " + upElevator.peek().getDestFloor() + " has been polled " + upElevator.poll());
                }

            }
        } else {
            if(downElevator != null){
                while(!downElevator.isEmpty() && currFloor == downElevator.peek().getDestFloor()){
                    timeAtDrop = time - downElevator.peek().getTimeCounter();
                    System.out.println("Passenger with starting floor: " + downElevator.peek().getStartFloor() + " & Destination floor: " + downElevator.peek().getDestFloor() + " has been polled " + downElevator.poll());
                }
            }
        }
        return timeAtDrop;
    }
```

## Installation
To install and clone my repository, first install
```bash
git clone git@github.com:AllenHo2/project02-Elevator.git
```
Then enter the project repository by using
```bash
cd AllenHo2/project02-Elevator
```
After that, enter the source directory
```bash
cd src
```
Type in this command to construct java classes to run on the command line
```bash
javac Main.java Passenger.java Floor.java Elevator.java
```
Then run the program by typing in (add any files after Main to run different properties)
```bash
java Main
```

## Connect
<a href="https://www.linkedin.com/in/allen-ho-b67a6725b/"><img width="58" alt="Linkedin_icon" src="https://github.com/AllenHo2/project02-Elevator/assets/112123839/38209676-0df8-4cdf-a99e-e172deb63854" href="https://www.linkedin.com/in/allen-ho-b67a6725b/"></img></a>
<a href="https://github.com/AllenHo2"> <img width="58" alt="Github_icon" src="https://github.com/AllenHo2/project02-Elevator/assets/112123839/e56b00ce-0fb2-4ee1-bde1-2aec3c393ecd" href="https://github.com/AllenHo2"></img></a>
