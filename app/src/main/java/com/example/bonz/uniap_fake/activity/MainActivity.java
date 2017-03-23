package com.example.bonz.uniap_fake.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.dbcontext.DBContext;
import com.example.bonz.uniap_fake.fragment.AttendanceFragment;
import com.example.bonz.uniap_fake.fragment.AttendanceStudentFragment;
import com.example.bonz.uniap_fake.fragment.NewsFragment;

import com.example.bonz.uniap_fake.fragment.NotificationsFragment;
import com.example.bonz.uniap_fake.fragment.OnGoingClassFragment;
import com.example.bonz.uniap_fake.fragment.SettingsFragment;
import com.example.bonz.uniap_fake.fragment.TimetableFragment;
import com.example.bonz.uniap_fake.model.AccountModel;
import com.example.bonz.uniap_fake.model.AttendanceModel;
import com.example.bonz.uniap_fake.model.ClassModel;
import com.example.bonz.uniap_fake.model.LectureModel;
import com.example.bonz.uniap_fake.model.SemesterModel;
import com.example.bonz.uniap_fake.model.StudentModel;
import com.example.bonz.uniap_fake.model.StudentOfClassModel;
import com.example.bonz.uniap_fake.model.SubjectModel;
import com.example.bonz.uniap_fake.model.SubjectOfClassModel;
import com.example.bonz.uniap_fake.model.TeacherModel;
import com.example.bonz.uniap_fake.other.CircleTransform;
import com.example.bonz.uniap_fake.other.Constanst;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // urls to load navigation header background image
    // and profile image
    private static final String urlNavHeaderBg = "http://api.androidhive.info/images/nav-menu-header-bg.jpg";
    private static final String urlProfileImg = "https://scontent.fhan2-1.fna.fbcdn.net/v/t31.0-8/16707217_1851396828461286_3736687948271040305_o.jpg?oh=b44547381106120c4a22b32932ffc60a&oe=59680F52";
    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_PHOTOS = "photos";
    private static final String TAG_ATTENDANCE = "take attendance";
    private static final String TAG_ONGOING_CLASS = "ongoing class";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    // index to identify current nav menu item
    public static int navItemIndex = 0;
    public static String CURRENT_TAG = TAG_HOME;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
//    private FloatingActionButton fab;
    private AccountModel accountModel;
    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;
    private DBContext dbContext;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    StudentModel infoAccountStudent;
    TeacherModel infoAccountTeacher;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("root");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbContext = DBContext.getInst();
        createSampleData();
        accountModel = dbContext.getAllAccount().get(0);
        if(accountModel.getRoll()==1){
            infoAccountStudent = dbContext.getAllStudent().get(0);
        }else  if(accountModel.getRoll()==2){
            infoAccountTeacher = dbContext.getAllTeacher().get(0);
        }

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
//        fab = (FloatingActionButton) findViewById(R.id.fab);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }

//        dbContext = DBContext.getInst();
//        createSampleData();
//        accountModel = dbContext.getAccountByID(1);
    }

    private void createSampleData() {
        dbContext.deleteAllStudent();
        dbContext.deleteAllTeacher();
        dbContext.deleteAllClass();
        dbContext.deleteAllAttendance();
        dbContext.deleteAllLectureModel();
        dbContext.deleteAllSemesterModel();
        dbContext.deleteAllStudentOfClass();
        dbContext.deleteAllSubjectModel();
        dbContext.deleteAllSubjectOfClassModel();
        dbContext.deleteAllLectureModel();


        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        //account

        AccountModel accountModel1 = AccountModel.create(1, "hoa", "1", 1);
        AccountModel accountModel2 = AccountModel.create(2, "la", "1", 1);
        AccountModel accountModel3 = AccountModel.create(3, "canh", "1", 1);
        AccountModel accountModel4 = AccountModel.create(4, "anhbt", "1", 2);
        dbContext.addAccount(accountModel1);
        dbContext.addAccount(accountModel2);
        dbContext.addAccount(accountModel3);
        dbContext.addAccount(accountModel4);
//        mDatabase.child("account").child(String.valueOf(accountModel1.getId())).setValue(accountModel1);
//        mDatabase.child("account").child(String.valueOf(accountModel2.getId())).setValue(accountModel2);
//        mDatabase.child("account").child(String.valueOf(accountModel3.getId())).setValue(accountModel3);
//        mDatabase.child("account").child(String.valueOf(accountModel4.getId())).setValue(accountModel4);
        //semester
        SemesterModel ses1 = SemesterModel.create(1, "Spring 2017", "05/01/2017", "30/04/2017");
        SemesterModel ses2 = SemesterModel.create(2, "Spring 2016", "05/01/2016", "30/04/2016");
        dbContext.addSemesterModel(ses1);
        dbContext.addSemesterModel(ses2);
//        mDatabase.child("semester").child(String.valueOf(ses1.getId())).setValue(ses1);
//        mDatabase.child("semester").child(String.valueOf(ses2.getId())).setValue(ses2);
        //temp teacher
        TeacherModel teacher1 = TeacherModel.create(5, "Bui", "Anh", "abc", "123", "TA123", "", null);
        TeacherModel teacher2 = TeacherModel.create(4, "name", "Teacher 4", "abc", "123", "TA123","", null);
        TeacherModel teacher3 = TeacherModel.create(3, "name", "Teacher 3", "abc", "123", "TA123", "", null);
        TeacherModel teacher4 = TeacherModel.create(2, "name", "Teacher 2", "abc", "123", "TA123", "", null);
        TeacherModel teacher5 = TeacherModel.create(1, "name", "Teacher 1", "abc", "123", "TA123", "", null);
        dbContext.addTeacher(teacher1);
        dbContext.addTeacher(teacher2);
        dbContext.addTeacher(teacher3);
        dbContext.addTeacher(teacher4);
        dbContext.addTeacher(teacher5);
//        mDatabase.child("teacher").child(String.valueOf(teacher1.getId())).setValue(teacher1);
        //temp student
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.huongntm5);
        StudentModel stu1 = StudentModel.create(1, "Nguyen", "hoa", "abc", "123", "SE03077", BitMapToString(bitmap), accountModel1);
        StudentModel stu2 = StudentModel.create(2, "Tran", "la", "abc", "123", "SE03078", BitMapToString(bitmap), accountModel2);
        StudentModel stu3 = StudentModel.create(3, "Dinh", "canh", "abc", "123", "SE03079", BitMapToString(bitmap), accountModel3);
        dbContext.addStudent(stu1);
        dbContext.addStudent(stu2);
        dbContext.addStudent(stu3);
//        mDatabase.child("student").child(String.valueOf(stu1.getId())).setValue(stu1);
//        mDatabase.child("student").child(String.valueOf(stu2.getId())).setValue(stu2);
//        mDatabase.child("student").child(String.valueOf(stu3.getId())).setValue(stu3);
        //temp class
        ClassModel class1 = ClassModel.create(5, "ES20102", ses1);
        dbContext.addClass(class1);
//       mDatabase.child("class").child(String.valueOf(class1.getId())).setValue(class1);
        //subject
        SubjectModel sub = SubjectModel.create(1, "PRM", "Mobile", 1);
        SubjectModel sub1 = SubjectModel.create(2, "ESS", "Embed System", 3);
        SubjectModel sub2 = SubjectModel.create(3, "SSC", "Soft Skill Comunication",3);
        SubjectModel sub3 = SubjectModel.create(4, "SPM", "Software Project Management",3);
        SubjectModel sub4 = SubjectModel.create(5, "VNR", "Vietnam Revolution",3);
        dbContext.addSubjectModel(sub);
//        mDatabase.child("subject").child(String.valueOf(sub.getId())).setValue(sub);
        dbContext.addSubjectModel(sub1);
        dbContext.addSubjectModel(sub2);
        dbContext.addSubjectModel(sub3);
        dbContext.addSubjectModel(sub4);
        //subject of class
        SubjectOfClassModel subOfClass1 = SubjectOfClassModel.create(1, sub, class1, teacher1);
        SubjectOfClassModel subOfClass2 = SubjectOfClassModel.create(2, sub1, class1, teacher2);
        SubjectOfClassModel subOfClass3 = SubjectOfClassModel.create(3, sub2, class1, teacher3);
        SubjectOfClassModel subOfClass4 = SubjectOfClassModel.create(4, sub3, class1, teacher4);
        SubjectOfClassModel subOfClass5 = SubjectOfClassModel.create(5, sub4, class1, teacher5);
        dbContext.addSubjectOfClassModel(subOfClass1);
//        mDatabase.child("subjectOfClass").child(String.valueOf(subOfClass1.getId())).setValue(subOfClass1);
        //student of class
        StudentOfClassModel stuClass1 = StudentOfClassModel.create(1, subOfClass1, stu1);
        StudentOfClassModel stuClass2 = StudentOfClassModel.create(2, subOfClass1, stu2);
        StudentOfClassModel stuClass3 = StudentOfClassModel.create(3, subOfClass1, stu3);
        dbContext.addStudentOfClass(stuClass1);
        dbContext.addStudentOfClass(stuClass2);
        dbContext.addStudentOfClass(stuClass3);
//        mDatabase.child("studentOfClass").child(String.valueOf(stuClass1.getId())).setValue(stuClass1);
//        mDatabase.child("studentOfClass").child(String.valueOf(stuClass2.getId())).setValue(stuClass2);
//        mDatabase.child("studentOfClass").child(String.valueOf(stuClass3.getId())).setValue(stuClass3);
        dbContext.addSubjectOfClassModel(subOfClass2);
        dbContext.addSubjectOfClassModel(subOfClass3);
        dbContext.addSubjectOfClassModel(subOfClass4);
        dbContext.addSubjectOfClassModel(subOfClass5);
        //temp lecture
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        LectureModel lec1 = LectureModel.create(1, c.getTime(), 5, subOfClass1);
        LectureModel lec2 = LectureModel.create(2, c.getTime(), 6, subOfClass1);
        LectureModel lec3 = LectureModel.create(3, c.getTime(), 1, subOfClass3);
        LectureModel lec4 = LectureModel.create(4, c.getTime(), 2, subOfClass4);
        c.add(Calendar.DAY_OF_WEEK, 3);
        LectureModel lec5 = LectureModel.create(5, c.getTime(), 3, subOfClass5);
        c.add(Calendar.DAY_OF_WEEK, 5);
        LectureModel lec6 = LectureModel.create(6, c.getTime(), 4, subOfClass1);
        LectureModel lec7 = LectureModel.create(7, c.getTime(), 5, subOfClass1);
        dbContext.addLectureModel(lec1);
        dbContext.addLectureModel(lec2);
        dbContext.addLectureModel(lec3);
        dbContext.addLectureModel(lec4);
//        mDatabase.child("lecture").child(String.valueOf(lec1.getId())).setValue(lec1);
//        mDatabase.child("lecture").child(String.valueOf(lec2.getId())).setValue(lec2);
//        mDatabase.child("lecture").child(String.valueOf(lec3.getId())).setValue(lec3);
//        mDatabase.child("lecture").child(String.valueOf(lec4.getId())).setValue(lec4);
        dbContext.addLectureModel(lec5);
        dbContext.addLectureModel(lec6);
        //attendance
        AttendanceModel attend1 = AttendanceModel.create(1, false, stu1, lec1);
        AttendanceModel attend2 = AttendanceModel.create(2, false, stu2, lec1);
        AttendanceModel attend3 = AttendanceModel.create(3, false, stu3, lec1);
        AttendanceModel attend4 = AttendanceModel.create(4, false, stu1, lec2);
        AttendanceModel attend5 = AttendanceModel.create(5, false, stu2, lec2);
        AttendanceModel attend6 = AttendanceModel.create(6, false, stu3, lec3);
        AttendanceModel attend7 = AttendanceModel.create(7, false, stu1, lec4);
        dbContext.addAttendance(attend1);
        dbContext.addAttendance(attend2);
        dbContext.addAttendance(attend3);
        dbContext.addAttendance(attend4);
        dbContext.addAttendance(attend5);
        dbContext.addAttendance(attend6);
        dbContext.addAttendance(attend7);
//        mDatabase.child("attendance").child(String.valueOf(attend1.getId())).setValue(attend1);
//        mDatabase.child("attendance").child(String.valueOf(attend2.getId())).setValue(attend2);
//        mDatabase.child("attendance").child(String.valueOf(attend3.getId())).setValue(attend3);
//        mDatabase.child("attendance").child(String.valueOf(attend4.getId())).setValue(attend4);
//        mDatabase.child("attendance").child(String.valueOf(attend5.getId())).setValue(attend5);
//        mDatabase.child("attendance").child(String.valueOf(attend6.getId())).setValue(attend6);
//        mDatabase.child("attendance").child(String.valueOf(attend7.getId())).setValue(attend7);
    }

    private String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    private Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {
        // name, website
        if(accountModel.getRoll()==1){
            txtName.setText(infoAccountStudent.getFirstName()+" "+infoAccountStudent.getLastName());
            txtWebsite.setText(infoAccountStudent.getRollNumber());
            imgProfile.setImageBitmap(StringToBitMap(infoAccountStudent.getPhoto()));
        }else  if(accountModel.getRoll()==2){
            txtName.setText(infoAccountTeacher.getFirstName()+" "+infoAccountTeacher.getLastName());
            txtWebsite.setText(infoAccountTeacher.getRollNumber());
            imgProfile.setImageBitmap(StringToBitMap(infoAccountTeacher.getPhoto()));
        }


        // loading header background image
        Glide.with(this).load(urlNavHeaderBg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);

        // Loading profile image
//        Glide.with(this).load(urlProfileImg)
//                .crossFade()
//                .thumbnail(0.5f)
//                .bitmapTransform(new CircleTransform(this))
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imgProfile);

        // showing dot next to notifications label
        navigationView.getMenu().getItem(1).setActionView(R.layout.menu_dot);
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button
//            toggleFab();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
//        toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // news
                NewsFragment newsFragment = new NewsFragment();
                return newsFragment;
            case 1:
                // photos
                TimetableFragment timetableFragment = new TimetableFragment();
                return timetableFragment;
            case 2:
                // attendance Fragment
                if (accountModel.getRoll() == Constanst.KEY_ROLL_STUDENT) {
                    AttendanceStudentFragment attendanceStudentFragment = new AttendanceStudentFragment();
                    return attendanceStudentFragment;
                } else if (accountModel.getRoll() == Constanst.KEY_ROLL_TEACHER) {
                    AttendanceFragment attendanceFragment = new AttendanceFragment();
                    return attendanceFragment;
                }
                // ongoing Class Fragment
            case 3:
                OnGoingClassFragment onGoingClassFragment = new OnGoingClassFragment();
                return onGoingClassFragment;
            case 4:
                // notifications fragment
                //Intent intent = new Intent(this, ProfileActivity.class);
                //startActivity(intent);
                //NotificationsFragment notificationsFragment = new NotificationsFragment();
                //return notificationsFragment;

            case 5:
                // settings fragment
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;
            default:
                return new NewsFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_timetable:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PHOTOS;
                        break;
                    case R.id.nav_attendance:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_ATTENDANCE;
                        break;
                    case R.id.nav_class:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_ONGOING_CLASS;
                        break;
                    case R.id.nav_notifications:
//                        navItemIndex = 4;
//                        CURRENT_TAG = TAG_NOTIFICATIONS;
                        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_about_us:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_privacy_policy:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
                        drawer.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
        /*if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }*/

        // when fragment is notifications, load the menu created for notifications
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.notifications, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
            return true;
        }

        // user is in notifications fragment
        // and selected 'Mark all as Read'
        if (id == R.id.action_mark_all_read) {
            Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();
        }

        // user is in notifications fragment
        // and selected 'Clear All'
        if (id == R.id.action_clear_notifications) {
            Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    // show or hide the fab
    /*private void toggleFab() {
        if (navItemIndex == 0)
            fab.show();
        else
            fab.hide();
    }*/
}