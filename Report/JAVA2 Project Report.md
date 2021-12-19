# JAVA2 Project Report	

## fountion and variable	

### data structure

- **continent**

  ```java
   String name;	
   long value;
  ```

  pie charts need continent dataType to express the total cases of every continent.

- Country

  ```java
  private String CountryCode;
  private String CountryName;
  private String Continent;
  public ArrayList<InfoPiece> infoList;
  private int total_cases;
  private int total_deaths;
  private int total_Vacs;
  ```

  Country dataTpye is to store all **infoPieces** of  this country and relevant information. 

- countryCase

  ```java
  String name;
  Long value;
  String Continent;
  ```

  Map chart needs countryCase dataType to represent the total cases of every country.

- countryData

  ```java
  ArrayList<String> date;
  ArrayList<Long>  newDeaths;
  ArrayList<Long>  newVACs;
  ArrayList<Long>  newCases;
  ```

  Line chart needs countryData dataTpye to express from the data on, in the next six day, what newDeaths, newVACs, newCases are.

- dynamicData

  ```java
  String date;
  String  country;
  long cases;
  ```

  dynamic chart needs dynamicData to express  the total cases of the country in this date.

- InfoPiece

  ```java
  public  String CountryCode;
  public  String CountryName;
  public  String Continent;
  public  Date date;
  public  Long newCases;
  public  Long totCases;
  public  Long newDeaths;
  public  Long totDeaths;
  public  Long newVACs;
  public  Long totVACs;
  ```

  InfoPiece dataTpye is to represent every line of the crawler data.

  

### data process

The whole process is as follows.

- accept request from the webpage

  **dataProcess**

  ```java
      @GetMapping("/continent")
      public String continent(){}
  
      @GetMapping("/country")
      public String country(@RequestParam String countryName,String date) throws ParseException {}
     
      @GetMapping("/map")
      public String map(){}
  
      @GetMapping("/dynamic")
      public String dynamic(){}
  
      @GetMapping("/table")
      public String table(@RequestParam String date, String group, String order) throws ParseException, NoSuchFieldException {}
  
  ```

- select the corresponding data according to the passed parameters.

  **DataUtil**

  ```java
   public static String mapChartData(ArrayList<InfoPiece> records){}
   
   public static String animaData(ArrayList<InfoPiece> records){}
   
   public static String pieChartData(ArrayList<InfoPiece> records){}
   
   public static String tableData(ArrayList<InfoPiece> records, String group, String order, Date date) throws NoSuchFieldException {}
   
   public static String tableData(ArrayList<InfoPiece> records, String group, String order, Date date) throws NoSuchFieldException {
  ```

- encapsulate the data and return it to the request as Json

  **DataToJson**

  ```java
   public static String tableData(ArrayList<InfoPiece> records, String group, String order, Date date) throws NoSuchFieldException {}
   
   public static String countryDataGet(Country country, Date date) throws ParseException {}
   
   public static String dynamicDataGet(ArrayList<InfoPiece> records) {}
   
   public static String mapDataGet(CountryCase[] country_cases) {}
   
   public static String tableDataGet(ArrayList<InfoPiece> records) {}
  ```



### database