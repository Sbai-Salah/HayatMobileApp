# RAPPORT DU PROJET | HAYAT APP
written with care and love 🩸
----
![banner](https://github.com/Sbai-Salah/Hayat_ver_1.0/blob/main/ScreensForReadme/banner.PNG)

| PARTIE 0 | PRESENTATION D'EQUIPE                         | [P0](https://github.com/Sbai-Salah/HayatMobileApp/edit/main/README.md#part-0-team-presentation)   |
|----------|-----------------------------------------------|--------------------------------------------------------------------------------------------|
| PARTIE 1 | POURQUOI CE PROJET ? ET DEMO VIDEO & OVERVIEW | [P1](https://github.com/Sbai-Salah/HayatMobileApp/edit/main/README.md#part-1-pourquoi-ce-projet-) |
| PARTIE 2 | EXPERIENCE UTILISATEUR + ARCHITECTURE         | [P2](https://github.com/Sbai-Salah/HayatMobileApp/edit/main/README.md#part-2-the-app-overview)    |
| PARTIE 3 | DEPENDENCIES (GRADLE)                         | [P3](https://github.com/Sbai-Salah/HayatMobileApp/edit/main/README.md#part-3)                     |




## PART 0: TEAM PRESENTATION

```
Professeur : Sarra Roubi.
Mini-Projet Dev Mobile Android 2023
Realiser par : 
       > CHAYMAE MHARZI 
       > SBAI SALAH
       > SGHIOURI IDRISSI MOHAMMED
```

![MAIN](https://github.com/Sbai-Salah/HayatMobileApp/blob/main/HAYAT/captures/main.PNG)

## PART 1: POURQUOI CE PROJET ?

Les réserves nationales de sang ne permettent de couvrir que quelques jours de besoins. La solidarité et l'entraide sont des traits communs à tous les Marocains. Pourtant, malgré la bonne volonté, beaucoup d'entre nous ne savent pas où aller ni comment s'y prendre. HAYAT est donc là pour répondre à toutes ces questions relatives au don de sang et vous indiquer le centre de prélèvement le plus proche. HAYAT est également à votre disposition lorsque vous avez besoin de sang pour publier votre annonce et la partager au plus grand nombre au Maroc.

> Une version web de cette application : [Github repo](https://sbai-salah.github.io/Hayat_ver_1.0/)
On a fait cette appli dans un projet du dev web. et on a penser a etendre l'application en mobile aussi.

Cette idée d'application existe au Maroc C'est une application appelée "Leena" mais le point faible de cette application est sa petite portée des citoyens. Nous espérons qu'après avoir développé cette application très basique pour continuer et ajouter plus de fonctionnalités et la rendre disponible pour aider plus de personnes.

## 📹 VIDEO PRESENTATIF DE L'APP : [WATCH DEMO](https://drive.google.com/drive/folders/1ADgx1auwux3MoSmI4iO94tn9t_LZmPMa)


## APP OVERVIEW :
---

![LOGIN](https://github.com/Sbai-Salah/HayatMobileApp/blob/main/HAYAT/captures/login.PNG)
![SIGNUP](https://github.com/Sbai-Salah/HayatMobileApp/blob/main/HAYAT/captures/signup.PNG)
![DONNERS](https://github.com/Sbai-Salah/HayatMobileApp/blob/main/HAYAT/captures/DonnersListView.PNG)
![DONNERSDETAILS](https://github.com/Sbai-Salah/HayatMobileApp/blob/main/HAYAT/captures/DonnationDetails.PNG)
![MAKEdonnation](https://github.com/Sbai-Salah/HayatMobileApp/blob/main/HAYAT/captures/MakeDonnation.PNG)
![makeREQUEST](https://github.com/Sbai-Salah/HayatMobileApp/blob/main/HAYAT/captures/MakeRequest.PNG)
![ACCOUNT](https://github.com/Sbai-Salah/HayatMobileApp/blob/main/HAYAT/captures/MyAccount.PNG)
![requestLIST](https://github.com/Sbai-Salah/HayatMobileApp/blob/main/HAYAT/captures/RequestsListView.PNG)
![requstDETAILS](https://github.com/Sbai-Salah/HayatMobileApp/blob/main/HAYAT/captures/RequestDetails.PNG)


### DESCRIPTION DU UX USER-EXPERIENCE :

Au premier moment, nous ouvrons l'application une interface (rouge) avec le logo de l'application au milieu, après 4 secondes, une intent commence et nous redirige vers l'interface de connexion (inscrire: Login/sign up) Soit vous connectez si vous avez un compte Ou vous créez un nouveau compte si c'est votre première fois. 

Après avoir fait cela, une autre intent commence et nous emmène sur la page principale qui n'est qu'une liste de donateurs `(car l'objectif principal de l'application est de faciliter la portée des personnes au dons de sang, nous avons fait notre page d'accueil)`. Dans la même page, nous avons en haut une dropdown qui a un autre élément qui nous amène aux demandes de sang (requests) pour voir ce que les autres demandent et quel type de sang ils ont besoin et toutes les informations nécessaires pour les atteindre.

Dans cette page principale, vous pouvez remarquer le bouton "Supprimer" (delete) apparaissant pour quelques éléments, ce bouton Supprimer est juste pour l'utilisateur actuel de l'application qui a le droit de supprimer sa demande quand il le souhaite. Pour chaque personne (item), nous avons son profil avec des informations détaillées et un bouton pour appeler cette personne directement en utilisant une intent explicite. Il existe également une autre interface qui est le profil utilisateur actuel qui peut mettre à jour ses données à tout moment et les enregistrer dans la base de données.


## PART 2: THE APP OVERVIEW

###  User Experience
---
 Presque toutes les applications Android contiennent plusieurs composants d'application, y compris des activités, des fragments, ... Ces composants d'application sont déclarés dans le `app manifest`. Le OS Android utilise ensuite ce fichier pour décider comment intégrer l'application dans l'UX globale du telephone. Étant donné qu'une application Android typique peut contenir plusieurs composants et que les utilisateurs interagissent souvent avec plusieurs applications en peu de temps, les applications doivent s'adapter à différents types de flux de travail (workflow) et de tâches d'utilisateur.  that's why `The simpler the UX be the smoothest use will be.`.
 
### discuss the different component declared in the `app manifest` : activities, fragments,...
---
**image : app manifest overview**
```XML
   </application> 
         ...

        <activity
            android:name=".SignupActivity"
            android:exported="false">
            <meta-data
                android:name="android.app.lib_name"
                android:value="" />
        </activity>
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name=".LoginActivity" />
        <activity android:name=".HomeActivity" />
        <activity android:name=".DonnationDetailsActivity" />
        <activity android:name=".AccountActivity" />
    </application>


```


### L'architecture d'application recommandée : UI Layer (View), Data layer(model)
---

#### UI layer ou la vue (View)
  La couche d'interface utilisateur affiche les données de l'application à l'écran et sert de point principal d'interaction utilisateur.
Dans notre applis nous avons 10 vues (voir les fichiers XML-interface presenter ci-dessus):
Pour l'organisation de ces vues on a utiliser les deux types de layouts :

```
1- Linear layout ( vertical ) pour le login.
2- Layouts avec l'adaptateur : listView (liste des donneur de sang )
```

On a utiliser `l'outil lint` pour optimiser la hierarchie des vues, afin d'augmenter la performance un peu ( on a eviter les DEEP layouts et utiliser les flater layouts comme le gridlayout et relative layouts)

Dans la vue du profile et dans tous les pages, on a utiliser un image circulaire pour le profile `circleImageView` we used this implementation provided in open source : [CircleImageView](https://github.com/hdodenhof/CircleImageView)


#### Data layer: App entry points - Activities

> introduction : 

La classe Activity est un composant crucial d'une application Android. Contrairement aux paradigmes de programmation dans lesquels les applications sont lancées avec une méthode main(), le système Android initie le code dans une instance Activity en appelant des méthodes de rappel (callback functions) spécifiques qui correspondent à des étapes spécifiques de son cycle de vie.

> Loaders

Une application qui utilise les chargeurs (loaders) comprend généralement les éléments suivants:

- Une FragmentActivity ou Fragment.
- Une instance du LoaderManager.
- Un CursorLoader pour charger des données soutenues par un `contentProvider`, qui est l'un des principaux éléments constitutifs des applications Android, fournissant du contenu aux applications. Ils encapsulent les données et les fournissent aux applications via l'interface contenu ResponseResolver. Un contentProvider n'est requis que lorsque nous devons partager des données entre plusieurs applications. Par exemple, les données de contacts sont utilisées par plusieurs applications et doivent être stockées dans un contentProvider. Si nous n'avons pas besoin de partager des données entre plusieurs applications, nous pouvons utiliser une base de données directement via `SQlitedatabase qui est le cas dans notre petite application.`

**image : un bout de code de connection avec la Database et le CRUD utiliser**
```java

 public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_REQUEST_TABLE);
        db.execSQL(CREATE_DONNATION_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        //Drop Resuest Table if exist
        db.execSQL(DROP_REQUEST_TABLE);
        //Drop Resuest Table if exist
        db.execSQL(DROP_DONNATION_TABLE);
        // Create tables again
        onCreate(db);
    }

    // This method is to create user record---------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public Boolean addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_FULLNAME, user.getFullName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PHONE, user.getPhone());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_SEXE, user.getSexe());
        values.put(COLUMN_USER_AGE, user.getAge());
        values.put(COLUMN_USER_BLOOD, user.getBlood());
        values.put(COLUMN_USER_ADRESSE, user.getAdresse());
        // Inserting Row
        long result = db.insert(TABLE_USER, null, values);
        db.close();
        if(result == -1) return false;
        return true;
```


#### Explication du code utiliser dans le CRUD:

Une difficulté que nous avons eu était lorsque nous essayons d'obtenir l'identification d'une personne (un item) dans la ListView (où les donneurs de sang présentent) afin d'afficher ses informations dans l'activité de «ViewProfile».

À la première étape, nous avons utilisé la méthode classique pour répondre à chaque élément cliqué dans un AdapterView qui est:


``` Java
  listView.setAdapter(adapter);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public boolean onItemClick(AdapterView<?> parent, View view, int position, long id) {
            
            //code here
        }
    });

```

Mais le problème était que nous voulions juste au moment où nous cliquons sur le bouton de "ViewProfile", nous allons chercher l'ID de cette personne qui n'était pas clair pour nous au début. Nous avons donc ajouté pour chaque bouton de chaque élément de ListEView une méthode OnClickListener `on a met ce click listener a l'interieur du listAdapter`. Après cela, nous obtenons l'ID de cette personne (item) en utilisant la position (voir code: «int position») et donc nous utilisons cet ID pour récupérer les données de la DB à l'aide d'une méthode définie (définie dans le DBHelper) `GetDonnorById ( ) `.

**La partie du code expliquer ci-dessus :**
```java
public class DonnationDetailsActivity extends AppCompatActivity {

    DatabaseHelper Database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donnation_details);

        // get Donnation or Request Details
        SharedPreferences settingsResult =this.getSharedPreferences("donnationDetails", MODE_PRIVATE);
        int donnationId = settingsResult.getInt("donnationId", 0);
        String donnationFullName = settingsResult.getString("donnationFullName", "");
        String donnationPhoneNumber = settingsResult.getString("donnationPhoneNumber", "");
        String donnationAge = settingsResult.getString("donnationAge", "");
        String donnationBloodGroup = settingsResult.getString("donnationBloodGroup", "");
        String donnationAdresse = settingsResult.getString("donnationAdresse", "");

        TextView ProfilLabelFullNameTextView = findViewById(R.id.ProfilLabelFullName);
        TextView FullNameTextView = findViewById(R.id.ProfilFullName);
        TextView PhoneTextView = findViewById(R.id.ProfilPhoneNumber);
        TextView AgeTextView = findViewById(R.id.ProfilAge);
        TextView BloodTextView = findViewById(R.id.ProfilBloodGroup);
        TextView AdresseTextView = findViewById(R.id.ProfilAdresse);

        ProfilLabelFullNameTextView.setText(donnationFullName);
        FullNameTextView.setText(donnationFullName);
        PhoneTextView.setText(donnationPhoneNumber);
        AgeTextView.setText(donnationAge);
        BloodTextView.setText(donnationBloodGroup);
        AdresseTextView.setText(donnationAdresse);

    }
```


En faisant cela et parce que nous avons l'habitude de travailler un peu avec PHP, nous avons cherché s'il y a quelque chose de similaire aux variables de session dans PHP en Java plus spécifique dans les intent. Et nous avons trouvé un moyen qui déclare un objet «editor» de l'interface `sharedPreferences.Editor`.

> Source : Pour plus dew details sur le sharedPreferences : [READ ME: SharedPreferences.Editor](https://developer.android.com/reference/android/content/SharedPreferences.Editor)
 
 
```java
    public Donnation getDonnationById(String donnationId){
        // array of columns to fetch
        String[] columns = {
                COLUMN_DONNATION_ID,
                COLUMN_DONNATION_FULLNAME,
                COLUMN_DONNATION_PHONE,
                COLUMN_DONNATION_BLOODGROUP,
                COLUMN_DONNATION_AGE,
                COLUMN_DONNATION_ADRESSE,
                COLUMN_DONNATION_USERID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_DONNATION_ID + " = ?";
        // selection arguments
        String[] selectionArgs = {donnationId};
        // query user table with condition
        Cursor cursor = db.query(TABLE_DONNATION, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        Donnation donnation = new Donnation(-1,"","","","","",-1);
        if (cursor.moveToFirst()){
            do {
                // Passing values
                donnation.setId(cursor.getInt(0));
                donnation.setFullName(cursor.getString(1));
                donnation.setPhoneNumber(cursor.getString(2));
                donnation.setBloodGroup(cursor.getString(3));
                donnation.setAge(cursor.getString(4));
                donnation.setAdresse(cursor.getString(5));
                donnation.setUserId(cursor.getInt(6));
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return donnation;
    }

```


## PART 3:
**image : PART OF GRADLE OF THE APP**
```gradle
android {
    compileSdk 33
    defaultConfig {
        applicationId "Android.App.Hayat"
        minSdkVersion 23
        targetSdkVersion 33
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    ...
}


dependencies {
     ...
    implementation 'de.hdodenhof:circleimageview:2.1.0'
    ...
}

```


 

