# Database
## 테이블과 쿼리
* 테이블:한 종류의 데이터가 저장되는 단위
* 쿼리:데이터를 정의,조작,제어하는 명령어
*** 
# Room
* SQLite의 기능을 모두 사용할 수 있고, DB로의 접근을 편하게 도와주는 라이브러리
* 참고:[[Android][Kotlin] Room 으로 DB 저장하기 by Yena Choi](https://blog.yena.io/studynote/2018/09/08/Android-Kotlin-Room.html)

## 사용하는 이유
* 원본 SQL의 컴파일 시간이 확실하지 않기때문에 SQL 데이터에 변화가 생기면 수동으로 업데이트 해야함(시간이 많이 소요되며 오류가 생길 수 있음)
* SQL 쿼리와 데이터 객체를 변환하기 위해서는 많은 상용구 코드를 작성해야함
## 구성요소
1. __Entity__:Database 안에 있는 테이블을 Java나 Kotlin클래스로 나타낸것(데이터 모델 클래스)
2. __DAO__(Database Acess Object):데이터베이스에 접근해서 실질적으로 insert,delete 등을 수행하는 메서드를 포함
3. __Database__:database holder를 포함하며 앱에 영구 저장되는 데이터와 기본 연결을 위한 주 액세스 지점. __RoomDatabase를 extend 하는 추상 클래스여야 하며, 테이블과 버전을 정의하는 곳

## Dependency 추가
* Java 언어를 사용할 경우
```gradle
/* when using Java */
dependencies {
    implementation "android.arch.persistence.room:runtime:1.1.1"
    annotationProcessor "android.arch.persistence.room:compiler:1.1.1"
    testImplementation "android.arch.persistence.room:testing:1.1.1"
}
```
* kotlin 언어를 사용할 경우
```gradle
/* when using Kotlin */
apply plugin: 'kotlin-kapt'

dependencies {
    implementation "android.arch.persistence.room:runtime:1.1.1"
    kapt "android.arch.persistence.room:compiler:1.1.1"
    kaptTest "android.arch.persistence.room:testing:1.1.1"
}
```
* RxJava나 Guava를 사용하는 경우
```gradle
/* optional - RxJava support for Room */
implementation "android.arch.persistence.room:rxjava2:1.1.1"

/* optional - Guava support for Room, including Optional and ListenableFuture */
implementation "android.arch.persistence.room:guava:1.1.1"
```
## Entity
* 데이터 모델에 무엇이 들어갈지 정의함
* 각각의 Entity는 고유 식별자인 __PrimaryKey(기본키)__ 가 반드시 필요함
```kotlin
@Entity(tableName = "cat")
class Cat(@PrimaryKey var id: Long?,
          @ColumnInfo(name = "catname") var catName: String?,
          @ColumnInfo(name = "lifespan") var lifeSpan: Int,
          @ColumnInfo(name = "origin") var origin: String
){
    constructor(): this(null,"", 0,"")
}
```
* PrimaryKey가 의미가 없다면 autoGenerate를 이용해 자동으로 생성되게 할 수도 있음
```kotlin
@Entity
class Cat(@PrimaryKey(autoGenerate = true) var id: Long?,
          @ColumnInfo ...
)
```
## DAO
* DB에 접근해 질의를 수행할 DAO 파일을 만듦
* Query를 메서드로 작성해주어야 함
```kotlin
@Dao
interface CatDao {
    @Query("SELECT * FROM cat")
    fun getAll(): List<Cat>

    /* import android.arch.persistence.room.OnConflictStrategy.REPLACE */
    @Insert(onConflict = REPLACE)
    fun insert(cat: Cat)

    @Query("DELETE from cat")
    fun deleteAll()
}
```
## Database
* Activity에서 호출하여 database객체를 반환하거나 삭제할 수 있도록 __getInstance()__, __destroyInstance()__ 메소드를 생성함
```kotlin
 @Database(entities = [Cat::class], version = 1)
abstract class CatDB: RoomDatabase() {
    abstract fun catDao(): CatDao

    companion object {
        private var INSTANCE: CatDB? = null

        fun getInstance(context: Context): CatDB? {
            if (INSTANCE == null) {
                synchronized(CatDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            CatDB::class.java, "cat.db")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
```
## Activity에서 Room에 접근
* __메인 쓰레드에서 Room DB에 접근하려고 하면 에러가 발생함__
```kotlin
class MainActivity : AppCompatActivity() {
    private var catDb : CatDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        catDb = CatDB.getInstance(this)

        val r = Runnable {
            // 데이터에 읽고 쓸때는 쓰레드 사용
        }

        val thread = Thread(r)
        thread.start()
    }
}
```