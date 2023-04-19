package com.example.informationtechnologydepartmentappv2

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.informationtechnologydepartmentappv2.structures.*

@SuppressLint("Recycle")
class DBAdapter(private val mContext: Context) {

    companion object {
        val DATABASE_NAME = "DB_IT_DEPT"
        val DATABASE_VERSION = 6
        val TABLE_USER	= "TABLE_USER"
        val TABLE_COURSE = "TABLE_COURSE"
        val TABLE_TIMETABLE	= "TABLE_TIMETABLE"
        val KEY_ID = "id"
        val KEY_NAME = "name"
        val KEY_EMAIL = "email"
        val KEY_PHONE = "phone"
        val KEY_PHOTO = "photo"
        val KEY_CODE = "code"
        val KEY_TITLE = "title"
        val KEY_CREDITS	= "credits"
        val KEY_PRE_REQUISITES = "pre_requisites"
        val KEY_DESCRIPTION	= "description"
        val KEY_DAY = "day"
        val KEY_BEGIN = "begin"
        val KEY_END	= "end"

        val QUERY_CREATE_USER	=	"CREATE TABLE IF NOT EXISTS $TABLE_USER" +
                "($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$KEY_NAME TEXT," +
                "$KEY_EMAIL TEXT," +
                "$KEY_PHONE INTEGER," +
                "$KEY_PHOTO TEXT)"
        val QUERY_UPGRADE_USER = "DROP TABLE IF EXISTS $TABLE_USER"

        val QUERY_CREATE_COURSE	= "CREATE TABLE IF NOT EXISTS $TABLE_COURSE" +
                "($KEY_CODE TEXT PRIMARY KEY, " +
                "$KEY_TITLE TEXT," +
                "$KEY_CREDITS INTEGER," +
                "$KEY_PRE_REQUISITES TEXT," +
                "$KEY_DESCRIPTION TEXT)"
        val QUERY_UPGRADE_COURSE = "DROP TABLE IF EXISTS $TABLE_COURSE"

        val QUERY_CREATE_TIMETABLE = "CREATE TABLE IF NOT EXISTS $TABLE_TIMETABLE" +
                "($KEY_CODE TEXT PRIMARY KEY, " +
                "$KEY_TITLE TEXT," +
                "$KEY_DAY TEXT," +
                "$KEY_BEGIN TEXT," +
                "$KEY_END TEXT)"
        val QUERY_UPGRADE_TIMETABLE	= "DROP TABLE IF EXISTS $TABLE_TIMETABLE"
    }

    private var mDBHelper: DBHelper? = null
    private var mDB: SQLiteDatabase? = null

    fun open(): DBAdapter {
        mDBHelper = DBHelper(mContext)
        mDB = mDBHelper?.writableDatabase
        return this
    }

    fun close() {
        try {
            mDBHelper?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getUserList(): MutableList<UserInfo> {
        val sql =   " SELECT	* " +
                " FROM		$TABLE_USER "
        val list: MutableList<UserInfo> = ArrayList()
        try {
            val cursor = mDB?.rawQuery(sql, null)
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val id = cursor.getLong(cursor.getColumnIndexOrThrow(KEY_ID))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME))
                    val email = cursor.getString(cursor.getColumnIndexOrThrow(KEY_EMAIL))
                    val phone = cursor.getString(cursor.getColumnIndexOrThrow(KEY_PHONE))
                    val photo = cursor.getString(cursor.getColumnIndexOrThrow(KEY_PHOTO))
                    val userInfo = UserInfo(name, email, phone, photo, id)
                    list.add(userInfo)
                    cursor.moveToNext()
                } while (!cursor.isAfterLast)
                cursor.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (list.isEmpty()) {
            list.add(UserInfo("Natalie Rose", "ithod@ucc.edu.jm", "+1 876 906-3000", "https://jamaica-gleaner.com/sites/default/files/styles/jg_article_image/public/media/article_images/2018/11/22/NatalieRoseA20181120LR.jpg?itok=40nQ29hP"))
            list.add(UserInfo("Otis Osbourne", "itfaculty@ucc.edu.jm", "+1 876 906-3000", "https://pbs.twimg.com/profile_images/1389979286854115329/p8KFeFpb_400x400.jpg"))
            list.add(UserInfo("Neil Williams", "itlecturer@ucc.edu.jm", "+1 876 906-3000", "https://img1.wsimg.com/isteam/ip/fde9f60c-773a-4e67-acd2-19344071b213/Neil%20Williams.jpg/:/rs=w:365,h:365,cg:true,m/cr=w:365,h:365"))
            list.add(UserInfo("Dr. Sajjad Rizvi", "srizvi@faculty.ucc.edu.jm", "+1 876 906-3000", "https://d2cax41o7ahm5l.cloudfront.net/mi/ocm/appliedphysics-mathematics-dr-syed-sajjad-hussain-rizvi-450678932.jpg"))
            list.add(UserInfo("Henry Osborne", "hosbourne@ucc.edu.jm", "+1 876 906-3000", "https://pbs.twimg.com/profile_images/1277350205873061888/e372ox-5_400x400.jpg"))
            list.forEach { insertUserInfo(it) }
        }
        return list
    }

    private fun insertUserInfo(userInfo: UserInfo): Long {
        val values = ContentValues()
        values.put(KEY_NAME, userInfo.name)
        values.put(KEY_EMAIL, userInfo.email)
        values.put(KEY_PHONE, userInfo.phone)
        values.put(KEY_PHOTO, userInfo.photo)
        return mDB?.insert(TABLE_USER, null, values) ?: -1
    }

    fun getCourseList(): MutableList<CourseInfo> {
        val sql =   " SELECT	* " +
                " FROM		$TABLE_COURSE "
        val list: MutableList<CourseInfo> = ArrayList()
        try {
            val cursor = mDB?.rawQuery(sql, null)
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val code = cursor.getString(cursor.getColumnIndexOrThrow(KEY_CODE))
                    val title = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TITLE))
                    val credits = cursor.getLong(cursor.getColumnIndexOrThrow(KEY_CREDITS))
                    val pre_requisites = cursor.getString(cursor.getColumnIndexOrThrow(KEY_PRE_REQUISITES))
                    val description = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DESCRIPTION))
                    val courseInfo = CourseInfo(code, title, credits, pre_requisites, description)
                    list.add(courseInfo)
                    cursor.moveToNext()
                } while (!cursor.isAfterLast)
                cursor.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (list.isEmpty()) {
            list.add(CourseInfo("ITT101", "Computer Information Systems", 3, "None",
                "This introductory course aimed to provide a foundation level understanding of Information Technology.\n" +
                        "The main concepts covered the identification and explanation of basic computer components, set up a basic workstation, " +
                        "conduct of basic software installation, establishment of basic network connectivity, " +
                        "the identification of compatibility issues and the prevention of basic security risks.\n" +
                        "In the practical section of the course students will get hands-on experience using office productivity tools and setup a basic workstation."
            ))
            list.add(CourseInfo("ITT103", "Programming Techniques", 3, "Computer Information Systems",
                "Students will learn the basics of programming in this course. Students will learn appropriate methods for programming designing and " +
                        "principles. Learning a programming language is not the primary goal; rather, the development of the logic and mental processes necessary for problem resolution is the primary goal\n.\n" +
                        "This course assumes that the student has no prior knowledge of programming; however successful students will be those with an aptitude for problem-solving.\n" +
                        "Programming Techniques serves as the foundation course for all other programming courses in the programme."
            ))
            list.add(CourseInfo("ITT200", "Object Oriented Programming using C++", 3, "Programming Techniques",
                "This course aims to broaden the student's knowledge of concepts and features of an object-oriented programming language.\n" +
                        "Students will be required to use these concepts to design solutions for real world problems."
            ))
            list.add(CourseInfo("ITT300", "Discrete Mathematics II", 3, "Discrete Mathematics I",
                "This course builds on the fundamentals of discrete mathematics covered in Discrete Structures I.\n" +
                        "The main focus will be on developing a sound theoretical foundation for further work in computer science and information science.\n" +
                        "The topics covered in this course will not be exhaustive to discrete structures but will provide the basis for " +
                        "pursuing other advanced courses in discrete structures and mathematics."
            ))
            list.add(CourseInfo("ITT304", "Database Management Systems II", 3, "Database Management Systems I",
                "This course is aimed at providing upper level undergraduate students with intermediate to " +
                        "advanced concepts in data modelling design and database administration.\n" +
                        "The course explores the variety of options available in database development and administration for " +
                        "current and future use on particular software platform technologies."
            ))
            list.add(CourseInfo("ITT308", "Internet Authoring II", 3, "Internet Authoring I",
                "This course continues from Internet Authoring I, covering some of the same topics in more depth.\n" +
                        "This course includes coverage of topics in networking technologies for the web, web " +
                        "UI design and site design, client-server architecture and client-side and server-side programming.\n" +
                        "It covers relevant topics in ecommerce, web security, and engineering concepts such as the three-tier architecture and frameworks for the web.\n" +
                        "It provides an introduction to mobile web issues and web multimedia."
            ))
            list.add(CourseInfo("ITT403", "Data Communication & Network II", 3, "Data Communication & Network I",
                "This course is aimed at providing the student with both the advanced theoretical and practical knowledge of the standards and" +
                        "techniques in Internetworking, Network security and introduction to Wireless Communications with particular reference to cellular networks.\n" +
                        "The course also pays special attention in laboratory sessions to IP routing and configuring of routers and switches. " +
                        "The CISCO IOS is used to illustrate the principles and concepts related to routing in TCP/IP networks." +
                        "Students gain knowledge in basic cryptography, fundamentals of computer and network "
            ))
            list.add(CourseInfo("ITT216", "Computer Essentials & Troubleshooting II", 3, "Computer Essentials & Troubleshooting I",
                "This course in computer troubleshooting and repairs is designed to give students hands-on experience in diagnosing\n" +
                        "and solving some of the common hardware and software problems that can occur during the normal usage of a computer."
            ))
            list.add(CourseInfo("ITT413", "Advanced Computer Graphics Design", 3, "Fundamental Computer Graphics Design",
                "The course builds upon foundational knowledge and skills in computer graphics\n" +
                        "students may be expected to complete complex projects and assignments that require\n" +
                        "them to demonstrate a high level of proficiency in using computer graphics tools and techniques.\n" +
                        "In addition to technical skills, students in an advanced computer graphics design course may also be expected to" +
                        "develop their critical thinking, problem-solving, and project management skills."
            ))
            list.add(CourseInfo("ITT405", "Human Computer Interaction & Interface Design", 3, "Introduction to Psychology, Systems Analysis & Designs I",
                "This course provides an introduction to the field of human-computer interaction (HCI), an " +
                        "interdisciplinary field that integrates cognitive psychology, design, computer science and others.\n" +
                        "Examining the human factors associated with information systems provides the students with knowledge to understand " +
                        "what influences usability and acceptance of Information Systems (IS). This course will examine human performance, components of " +
                        "technology, methods and techniques used in design and evaluation of IS.\n" +
                        "Societal impacts of HCI such as accessibility will also be discussed.\n" +
                        "User-centered design methods will be introduced and evaluated.\n" +
                        "This course will also introduce students to the contemporary technologies used in empirical evaluation methods."
            ))
            list.forEach { insertCourseInfo(it) }
        }
        return list
    }

    private fun insertCourseInfo(courseInfo: CourseInfo): Long {
        val values = ContentValues()
        values.put(KEY_CODE, courseInfo.code)
        values.put(KEY_TITLE, courseInfo.title)
        values.put(KEY_CREDITS, courseInfo.credits)
        values.put(KEY_PRE_REQUISITES, courseInfo.pre_requisites)
        values.put(KEY_DESCRIPTION, courseInfo.description)
        return mDB?.insert(TABLE_COURSE, null, values) ?: -1
    }

    fun getTimeTableList(): MutableList<TimeTableInfo> {
        val sql =   " SELECT	* " +
                " FROM		$TABLE_TIMETABLE "
        val list: MutableList<TimeTableInfo> = ArrayList()
        try {
            val cursor = mDB?.rawQuery(sql, null)
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val code = cursor.getString(cursor.getColumnIndexOrThrow(KEY_CODE))
                    val title = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TITLE))
                    val day = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DAY))
                    val begin = cursor.getString(cursor.getColumnIndexOrThrow(KEY_BEGIN))
                    val end = cursor.getString(cursor.getColumnIndexOrThrow(KEY_END))
                    val timeTableInfo = TimeTableInfo(code, title, day, begin, end)
                    list.add(timeTableInfo)
                    cursor.moveToNext()
                } while (!cursor.isAfterLast)
                cursor.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (list.isEmpty()) {
            list.add(TimeTableInfo("ITT101", "Computer Information Systems", "Mon", "06:00 PM", "09:00 PM"))
            list.add(TimeTableInfo("ITT103", "Programming Techniques", "Tue", "06:00 PM", "08:00 PM"))
            list.add(TimeTableInfo("ITT200", "Object Oriented Programming using C++", "Sun", "06:00 PM", "08:00 PM"))
            list.add(TimeTableInfo("ITT300", "Discrete Mathematics II", "Sat", "08:00 PM", "10:00 PM"))
            list.add(TimeTableInfo("ITT304", "Database Management Systems II", "Wed", "08:00 PM", "10:00 PM"))
            list.add(TimeTableInfo("ITT308", "Internet Authoring II", "Thu", "08:00 PM", "10:00 PM"))
            list.add(TimeTableInfo("ITT309", "Computer Security", "Sun", "12:00 PM", "02:00 PM"))
            list.add(TimeTableInfo("ITT423", "IT Capstone Project II", "Sat", "06:00 PM", "08:00 PM"))
            list.add(TimeTableInfo("ITT401", "Intelligent Systems", "Sun", "02:00 PM", "04:00 PM"))
            list.add(TimeTableInfo("ITT405", "Human Computer Interaction & Interface Design", "Wed", "08:00 PM", "10:00 PM"))
            list.forEach { insertTimeTableInfo(it) }
        }
        return list
    }

    private fun insertTimeTableInfo(timeTableInfo: TimeTableInfo): Long {
        val values = ContentValues()
        values.put(KEY_CODE, timeTableInfo.code)
        values.put(KEY_TITLE, timeTableInfo.title)
        values.put(KEY_DAY, timeTableInfo.day)
        values.put(KEY_BEGIN, timeTableInfo.begin)
        values.put(KEY_END, timeTableInfo.end)
        return mDB?.insert(TABLE_TIMETABLE, null, values) ?: -1
    }
}