# ComputerRecognitionSystems

---

## data gathered from:  
-> [Reuters-21578 Text Categorization Collection - UCI Machine Learning Repository](https://archive.ics.uci.edu/dataset/137/reuters+21578+text+categorization+collection)

---

# PROJECT 1

>Develop an application using JDK LTS technology (latest version) for text document classification using the k-NN method. The application comprises two modules:

### Feature extraction module:

Operating on a provided set of texts.
>Extracts features from each text, representing them as vectors of selected feature values.
Selects a minimum of 10 features, ensuring their independence from the number of texts in the database.
Includes at least two features expressed as texts, independent of the text corpus size.
The text corpus can be obtained from here.
k-NN classifier module:

Operates solely on feature vectors representing texts.
> Accepts parameters such as the value of k, the ratio for dividing the vector set into training and testing sets, the selected features for classification, and the metric/similarity measure used in the k-NN method.
The application must have a user interface for inputting classification parameters and presenting results. It should be developed in Java.

---

## task1 - Classification supervised by k-NN method. Feature extraction, feature vectors.  

### selected classifiers:
* places
  * geography
     * lakes
     * mountains
     * [...]
  * cities
  * [...]
* architecture
  * buildings
  * monuments
    * stone hange
    * world trade center
    * statue of liberty
  * [...]
* names
  * as proper names (U.S. USA, america)
* dates ???
  * history
  * politicks
 
> note to selve:  
> remember that some dates are important for more than 1 country (see for example ww2)  

* peoples
  * generals
  * politics
  * athlests (are there any?)
  * starts (are there any?)
  * [...]
* currencies

> note to selve:  
> as symbols and signs  
> ($ == dlr == dollar == dollars)

* popular names ???
  * Abigale == USA name
  * Jebie suki na bosaka == asian name
  * Hans == German name
* characteristic words (states, lands, shire)
  * states
  * lands
  * shire
  * [...]
* text length (useless as fuck but we can test it anyway)
* events (like hiroshima incident)
* Organizations ???

> note to selve:  
> problem is that there are naby international orgs

---
.