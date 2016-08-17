package application;
	
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Date;



public class MainUI extends Application{
	static Connection con;
	static MainUI sdb;
	private Stage primaryStage;
	static int selectfid;
	static int selectsid;
	static String newcname;
	static String newmeetsat;
	static String newroom;
	static int newyear;
	static int courseID;
	static int evaID;
	static int selectcid;
	static String semester;
	static String type;
	static Date duedate;
	static String evameetat;
	static int weightage;
	static int onegrade;
	static int selecteid;
	
	
    private BorderPane rootLayout;
    @FXML
    private AnchorPane coursepane;
    @FXML
    private RadioButton faculty;
    
    @FXML 
    private RadioButton student;
    @FXML 
    private RadioButton admin;
    @FXML
	private ChoiceBox<String> choice;
    @FXML
	private ChoiceBox<String> choicestu;
    @FXML
	private ChoiceBox<String> cchoice;
    @FXML
	private ChoiceBox<String> schoice;
    @FXML
	private Button admin1;
    @FXML
	private Button admin2;
    @FXML 
    private Button calendar;
    @FXML 
    private Button mycourse;
    @FXML 
    private Button mygrade;
    @FXML 
    private Button assign;
    @FXML 
    private Button modifycourse;
    @FXML 
    private Button createcourse;
    @FXML 
    private Button modifyeva;
    @FXML 
    private Button createeva;
    @FXML 
    private Button entergrade;
    @FXML 
    private Button reportclass;
    @FXML 
    private Button reporteva;
    
    @FXML
    private TextArea textarea;
    @FXML
    private Label labelselectid;
    
    @FXML
    private Label lcoursename;
    @FXML
    private Label lroom;
    @FXML
    private Label lmeetsat;
    @FXML
    private Label lyear;
    @FXML
    private Label lsemester;
    @FXML
    private Label lcourseid;
    @FXML
    private Label lcm;
    @FXML
    private TextField tcoursename;
    @FXML
    private TextField tcourseid;
    @FXML
    private TextField tyear;
    @FXML
    private TextField tsemester;
    @FXML
    private TextField tmeetsat;
    @FXML
    private TextField troom;
    @FXML 
    private Button okbutton;
    @FXML 
    private Button updatecname;
    @FXML 
    private Button updateyear;
    @FXML 
    private Button updatesem;
    @FXML 
    private Button updatemeetsat;
    @FXML 
    private Button updateroom;
    @FXML
    private Label lcmeva;
    @FXML
    private Label levaid;
    @FXML
    private Label ltype;
    @FXML
    private Label lcourseideva;
    @FXML
    private Label lweightage;
    @FXML
    private Label lduedate;
    @FXML
    private Label lmeetat;
   
    @FXML
    private TextField tevatype;
    @FXML
    private TextField tevaid;
    @FXML
    private TextField tduedate;
    @FXML
    private TextField tevameetat;
    @FXML
    private TextField tweightage;
    @FXML
    private TextField tevacourseid;
    @FXML
    private Button okbuttoneva;
    @FXML 
    private Button updateevatype;
    @FXML 
    private Button updateduedate;
    @FXML 
    private Button updatemeetateva;
    @FXML 
    private Button updateweightage;
    @FXML 
    private Button requiredevaid;
    @FXML 
    private Button requiredcourseid;
    
    @FXML 
    private Button gradeconfirm;
    @FXML
    private TextField newgrade;
    @FXML
    private ChoiceBox<String> stuevagrade;
    @FXML
    private ChoiceBox<String> evachoicegrade; 
    @FXML
    private Label levagrade;
    @FXML
    private Label lstugrade;
    @FXML
    private Label lgrade;

    @FXML 
    private Button assignconfirm;
    @FXML 
    private Label lcchoice;
    @FXML 
    private Label lschoice;
    
    public MainUI(){
    	
    	try {
		Class.forName( "oracle.jdbc.driver.OracleDriver" );
		}
		catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		try {
			con =
					DriverManager.getConnection( "jdbc:oracle:thin:@claros.cs.purdue.edu:1524:strep","ji43", "m4NrxZU7" );
		}
		catch ( SQLException e ){
			e.printStackTrace();
		}
		
    }
    public void setVisibilityInGeneral(boolean val){
    	textarea.clear();
    	setCreateModifyCourseVisibility(val);
    	setModifyCourseVisibility(val);
    	setCreateModifyEvaVisibility(val);
    	setModifyEvaVisibility(val);
    	setAssignVisibility(val);
    	setGradeVisibility(val);
    }
    public void setAdminUIVisibility(boolean val){
    	admin1.setVisible(val);
    	admin2.setVisible(val);
    }
    public void setStudentUIVisibility(boolean val){
    	calendar.setVisible(val);
        mycourse.setVisible(val);
        mygrade.setVisible(val);
    }
    public void setFacultyUIVisibility(boolean val){
    	assign.setVisible(val);
        modifycourse.setVisible(val);
        createcourse.setVisible(val);
        modifyeva.setVisible(val);
        createeva.setVisible(val);
        entergrade.setVisible(val);
        reportclass.setVisible(val);
        reporteva.setVisible(val);
    }
    public void setCreateCourseVisibility(boolean val){
    	lcoursename.setVisible(val);
    	lroom.setVisible(val);
    	lmeetsat.setVisible(val);
    	lyear.setVisible(val);
    	lsemester.setVisible(val);
    	lcourseid.setVisible(val);
    	lcm.setVisible(val);
    	tcoursename.setVisible(val);
    	troom.setVisible(val);
    	tmeetsat.setVisible(val);
    	tyear.setVisible(val);
    	tsemester.setVisible(val);
    	tcourseid.setVisible(val);
    }
    public void setCreateModifyCourseVisibility(boolean val){
    	setCreateCourseVisibility(val);
    	okbutton.setVisible(val);
    }

    public void setModifyCourseVisibility(boolean val){
    	setCreateCourseVisibility(val);
    	requiredcourseid.setVisible(val);
    	updatecname.setVisible(val);
    	updateyear.setVisible(val);
    	updatesem.setVisible(val);
    	updatemeetsat.setVisible(val);
    	updateroom.setVisible(val);
    	okbutton.setVisible(false);
    }
    public void setCreateModifyEvaVisibility(boolean val){
    	setCreateEvaVisibility(val);
    	okbuttoneva.setVisible(val);
    }
    private void setCreateEvaVisibility(boolean val) {
    	tevatype.setVisible(val);
    	tevaid.setVisible(val);
    	tduedate.setVisible(val);
    	tevameetat.setVisible(val);
    	tweightage.setVisible(val);
    	tevacourseid.setVisible(val);
    	okbuttoneva.setVisible(val);
    	lcmeva.setVisible(val);
    	levaid.setVisible(val);
    	ltype.setVisible(val);
    	lcourseideva.setVisible(val);
    	lweightage.setVisible(val);
    	lduedate.setVisible(val);
    	lmeetat.setVisible(val);
    }

    private void setModifyEvaVisibility(boolean val) {
	setCreateEvaVisibility(val);
	requiredevaid.setVisible(val);
	updateevatype.setVisible(val);
    	updateduedate.setVisible(val);
    	updatemeetateva.setVisible(val);
    	updateweightage.setVisible(val);
    	okbuttoneva.setVisible(false);
		
    }
    private void setAssignVisibility(boolean val) {
		schoice.setVisible(val);
		cchoice.setVisible(val);
		lschoice.setVisible(val);
		lcchoice.setVisible(val);
		assignconfirm.setVisible(val);	
	}

    private void setGradeVisibility(boolean val) {
    	stuevagrade.setVisible(val);
		evachoicegrade.setVisible(val);
		levagrade.setVisible(val);
		lstugrade.setVisible(val);
		lgrade.setVisible(val);
		newgrade.setVisible(val);
		gradeconfirm.setVisible(val);	
	}
    public void setSelectFacultyIDVisibility(boolean val){
    	choice.setVisible(val);
    	labelselectid.setVisible(val);
    }
    public void setSelectStudentIDVisibility(boolean val){
    	choicestu.setVisible(val);
    	labelselectid.setVisible(val);
    }
    
	/* Simple SQL statement: Names of students in department */
    public ArrayList<String> getFaculty() {
		ArrayList<String> fname = new ArrayList<String>();
		String query = "Select fid from Faculty";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery( query );
			while ( rs.next() ) {
				String name = rs.getString( "fid" );
				System.out.println(name);
				fname.add(name);
			}
			rs.close();
			stmt.close();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}
		return fname;
	}
	
	public ArrayList<String> getStudent() {
		ArrayList<String> sname = new ArrayList<String>();
		String query = "Select sid from Student";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery( query );
			while ( rs.next() ) {
				String name = rs.getString( "sid" );
				System.out.println(name);
				sname.add(name);
			}
			rs.close();
			stmt.close();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}
		return sname;
	}
	
	public ArrayList<String> getEvaluation() {
		ArrayList<String> ename = new ArrayList<String>();
		String query = "Select eid from Evaluation";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery( query );
			while ( rs.next() ) {
				String name = rs.getString( "eid" );
				ename.add(name);
			}
			rs.close();
			stmt.close();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}
		return ename;
	}
	
	public ArrayList<String> getCourse() {
		ArrayList<String> clist = new ArrayList<String>();
		String query = "Select cid from Course";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery( query );
			while ( rs.next() ) {
				String id = rs.getString( "cid" );
				System.out.println(id);
				clist.add(id);
			}
			rs.close();
			stmt.close();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}
		return clist;
	}
	
	public ArrayList<String> getDepartmentReport() {
		ArrayList<String> sname = new ArrayList<String>();
		String query = "Select headname, dname from Department";
		try {
			Statement stmt = con.createStatement();
			textarea.clear();
			textarea.appendText("(Department Name)      (HeadOfTheDepartment)\n");
			ResultSet rs = stmt.executeQuery( query );
			while ( rs.next() ) {
				
				String name = rs.getString( "headname" );
				String name2 = rs.getString( "dname" );
				
				textarea.appendText(name2+"                      "+name+"\n");
				sname.add(name);
			}
			rs.close();
			stmt.close();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}
		return sname;
	}
	
	public ArrayList<String> getFacultyReport() {
		textarea.clear();
		ArrayList<String> sname = new ArrayList<String>();
		String query = "select dname, fid from (Department left outer join Work on Department.depid=work.depid)";
		try {
			Statement stmt = con.createStatement();
			textarea.clear();
			textarea.appendText("(Department Name)      (Faculty Name)\n");
			ResultSet rs = stmt.executeQuery( query );
			while ( rs.next() ) {
				
				String name = rs.getString( "dname" );
				textarea.appendText("\n"+name+"\n");
				int fid = Integer.parseInt(rs.getString( "fid" ));
				query = "select fname from Faculty where fid="+fid;
				Statement stmt1 = con.createStatement();
				ResultSet rs1 = stmt1.executeQuery( query );
				while ( rs1.next() ) {
					String fname = rs1.getString( "fname" );
					textarea.appendText("                      "+fname+"\n");
				}
				rs1.close();
				stmt1.close();
				sname.add(name);
			}
			rs.close();
			stmt.close();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}
		return sname;
	}

	
	/* Nested Query: Names of students enrolled in a course */
	public void getStudentsInClass( String cname ) {
		String query = "Select snum from Enrolled where cname = '" + cname + "'";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery( query );
			while ( rs.next() ) {
				int snum = rs.getInt( "snum" );
				String nestedQuery = "Select sname from Student where snum=" + snum;
				Statement stmtNested = con.createStatement();
				ResultSet rsNested = stmtNested.executeQuery( nestedQuery );
				while ( rsNested.next() ) {
					String sname = rsNested.getString( "sname" );
					System.out.println( sname );
				}
				rsNested.close();
				stmtNested.close();
			}
			rs.close();
			stmt.close();
		}
		catch ( SQLException e ) {}
	}
	
	public void createNewCourse() {
		String query = "insert into Course " + 
						" (cname,semester,year,meetat,room,fid,cid)" 
						+ " values (?,?,?,?,?,?,?)";
		
		try {
			
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, newcname );
            stmt.setString(2, semester);
            stmt.setInt(3, newyear);
            stmt.setString(4, newmeetsat);
            stmt.setString(5, newroom);
            stmt.setInt(6, selectfid);
            stmt.setInt(7, courseID );
            stmt.executeUpdate();
			stmt.close();
		}
		catch ( SQLException e ) {}
	}
	
	@FXML
    private String FacultyIsSelected(ActionEvent event) {
    	if(student.isSelected() || admin.isSelected()){
    		student.setSelected(false);
    		admin.setSelected(false);
    		setSelectStudentIDVisibility(false);
    	}
    	setAdminUIVisibility(false);
    	setStudentUIVisibility(false);
    	setFacultyUIVisibility(false);
    	setVisibilityInGeneral(false);
    
    	ArrayList<String> results = sdb.getFaculty();
    	choice.setItems(FXCollections.observableArrayList(results));
    	setSelectFacultyIDVisibility(true);
		choice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(newValue.intValue() >=0){
					 selectfid = Integer.parseInt(results.get(newValue.intValue()));
					 setFacultyUIVisibility(true);

				}
			}
		});
		return "Hello";
		
	}
	
	@FXML
    private void StudentIsSelected(ActionEvent event) {
    	if(faculty.isSelected() || admin.isSelected()){
    		faculty.setSelected(false);
    		admin.setSelected(false);
    		setSelectFacultyIDVisibility(false);
    	}
    	setVisibilityInGeneral(false);
    	setFacultyUIVisibility(false);
    	setAdminUIVisibility(false);
    	ArrayList<String> result = sdb.getStudent();
        
    	choicestu.setItems(FXCollections.observableArrayList(result));
    	setSelectStudentIDVisibility(true);
		choicestu.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(newValue.intValue() >=0){
					 selectsid = Integer.parseInt(result.get(newValue.intValue()));
					 setStudentUIVisibility(true);
				}
			}
		});
		
			
	}
	@FXML
    private void AdminIsSelected(ActionEvent event) {
    	if(student.isSelected() || faculty.isSelected()){
    		student.setSelected(false);
    		faculty.setSelected(false);
    		setSelectFacultyIDVisibility(false);
    		setSelectStudentIDVisibility(false);
    	}
    	setVisibilityInGeneral(false);
    	setAdminUIVisibility(true);
    	setStudentUIVisibility(false);
    	setFacultyUIVisibility(false);    	
	}
	@FXML
	private void handleDepartmentReport	(ActionEvent event){
		getDepartmentReport();
	}
	@FXML
	private void handleFacultyReport(ActionEvent event){
		getFacultyReport();
		
	}
	@FXML
	private void handleEvaluationCalendar(ActionEvent event){
		System.out.println("Display my evaluations "+selectsid+" "+selectcid);
		textarea.clear();
		int tempcid = 0;
		String query = "select cid from Assign where sid = "+selectsid;
		try {
			Statement stmt = con.createStatement();
			textarea.appendText("CourseID	EvaID     Tyoe		Weightage	DueDate				Room \n");
			ResultSet rs = stmt.executeQuery( query );
			while ( rs.next() ) {
				tempcid = Integer.parseInt(rs.getString( "cid" ));
				String query1 = "select eid, evaluationType,weightage,duedate from Evaluation where cid = "+ tempcid;
				Statement stmt1 = con.createStatement();
				ResultSet rs1 = stmt1.executeQuery( query1 );
				while ( rs1.next() ) {
					String eid = rs1.getString( "eid" );
					String etype = rs1.getString( "evaluationType" );
					int eweightage = rs1.getInt( "weightage" );
					String eduedate = rs1.getString( "duedate" );
					textarea.appendText(tempcid+"	    "+eid +"		"+etype+"	"+
										eweightage+"		"+ eduedate+"\n");
				}
				rs1.close();
				stmt1.close();
			}
			rs.close();
			stmt.close();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleCreateCourse(ActionEvent event){
		setVisibilityInGeneral(false);
		setCreateModifyCourseVisibility(true);	
	}

	@FXML
	private void handleNewCourseName(ActionEvent event){
		newcname = tcoursename.getText();
		System.out.println(newcname);
	}
	@FXML
	private void handleNewCourseID(ActionEvent event){
		courseID = Integer.parseInt(tcourseid.getText());
		System.out.println(courseID);
	}
	@FXML
	private void handleNewYear(ActionEvent event){
		newyear = Integer.parseInt(tyear.getText());
		System.out.println(newyear);
	}
	@FXML
	private void handleNewSemester(ActionEvent event){
		semester = tsemester.getText();
		System.out.println(semester);
	}
	@FXML
	private void handleNewMeetsAt(ActionEvent event){
		newmeetsat = tmeetsat.getText();
		System.out.println(newmeetsat);
	}
	@FXML
	private void handleNewRoom(ActionEvent event){
		newroom = troom.getText();
		System.out.println(newroom);
		//setCreateModifyCourseVisibility(false);
	}
	@FXML
    private void ComfirmCreateCourse(ActionEvent event){
		setCreateModifyCourseVisibility(false);
		createNewCourse();
	}
	@FXML
    private void handleRequiredCourseID(ActionEvent event){
		courseID = Integer.parseInt(tcourseid.getText());
	}
    
	@FXML
    private void updateCourseName(ActionEvent event){
		String query = "Update Course "+
						"SET cname = ? where cid = ? and fid = ?";
		try {
			PreparedStatement update = con.prepareStatement(query);
			update.setString(1, newcname);
			update.setInt(2, courseID);
			update.setInt(3, selectfid);
			update.executeUpdate();
			con.commit();
			update.close();
		}
		catch ( SQLException e ) {System.out.println(e.getMessage());}
	}
	@FXML
    private void updateYear(ActionEvent event){
		String query = "Update Course "+
						"SET year = ? where cid = ? and fid = ?";
		try {
			PreparedStatement update = con.prepareStatement(query);
			update.setInt(1, newyear);
			update.setInt(2, courseID);
			update.setInt(3, selectfid);
			update.executeUpdate();
			con.commit();
			update.close();
		}
		catch ( SQLException e ) {System.out.println(e.getMessage());}
	}
	@FXML
    private void updateSemester(ActionEvent event){
		String query = "Update Course "+
						"SET semester = ? where cid = ? and fid = ?";
		try {
			PreparedStatement update = con.prepareStatement(query);
			update.setString(1, semester);
			update.setInt(2, courseID);
			update.setInt(3, selectfid);
			update.executeUpdate();
			con.commit();
			update.close();
		}
		catch ( SQLException e ) {System.out.println(e.getMessage());}
	}
	@FXML
    private void updateMeetsAt(ActionEvent event){
		String query = "Update Course "+
						"SET meetat = ? where cid = ? and fid = ?";
		try {
			PreparedStatement update = con.prepareStatement(query);
			update.setString(1, newmeetsat);
			update.setInt(2, courseID);
			update.setInt(3, selectfid);
			update.executeUpdate();
			con.commit();
			update.close();
		}
		catch ( SQLException e ) {System.out.println(e.getMessage());}
	}
	@FXML
    private void updateRoom(ActionEvent event){
		System.out.println( courseID + " "+newroom);
		String query = "Update Course "+
						"SET room = ? where cid = ? and fid = ?";
		try {
			PreparedStatement update = con.prepareStatement(query);
			update.setString(1, newroom);
			update.setInt(2, courseID);
			update.setInt(3, selectfid);
			update.executeUpdate();
			con.commit();
			update.close();
		}
		catch ( SQLException e ) {System.out.println(e.getMessage());}
	}
	@FXML
	private void ConfirmCreateEva(ActionEvent event){
		String query = "insert into Evaluation " + 
				" (eid,evaluationType,fid,cid,weightage,duedate,meetingroom)" 
				+ " values (?,?,?,?,?,?,?)";
		System.out.println("Start! ");
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, evaID);
		    stmt.setString(2, type);
		    stmt.setInt(3,selectfid);
		    stmt.setInt(4, courseID);
		    stmt.setInt(5, weightage);
		    Timestamp timestamp = new Timestamp(duedate.getTime());
		    stmt.setTimestamp(6, timestamp);
		    stmt.setString(7, evameetat );
		    stmt.executeUpdate();
			stmt.close();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}	
	}
	
    @FXML
    private void handleNewEvaluationType(ActionEvent event){
    	type = tevatype.getText();
	}

    @FXML
    private void handleNewEvaluationID(ActionEvent event){
		evaID= Integer.parseInt(tevaid.getText());
	}
    @FXML
    private void handleNewDueDate(ActionEvent event){
    	String sdate= tduedate.getText();
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    	try {
			duedate = format.parse(sdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    @FXML
    private void handleNewEvaMeetAt(ActionEvent event){
    	evameetat = tevameetat.getText();
    	System.out.println(evameetat);
	}
    @FXML
    private void handleNewWeightage(ActionEvent event){
		weightage = Integer.parseInt(tweightage.getText());
	}
    @FXML
    private void handlefindEvaCourseID(ActionEvent event){
    	courseID = Integer.parseInt(tevacourseid.getText());
		System.out.println(courseID);
	}
    @FXML
    private void handleMyGrade(ActionEvent event){
		System.out.println("Display my courses "+selectsid);
		textarea.clear();
		int tempeid = 0;
		int tempgrade = 0;
		int total = 0;  //mo
		String temptype = "";
		int tempweightage = 0;
		String query = "select cid, cname from Assign where sid = "+ selectsid;
		try {
			Statement stmt = con.createStatement();
			textarea.appendText("CourseName        EvaID	Type    Grade	Weightage    Total\n");
			ResultSet rs = stmt.executeQuery( query );
			while ( rs.next() ) {
				String tempcname = rs.getString("cname");
				int tempcid = rs.getInt( "cid" );
				String query1 = "select eid, grade from Grade where cid = "+tempcid+" and sid = "+selectsid; 
				Statement stmt1 = con.createStatement();
				ResultSet rs1 = stmt1.executeQuery( query1 );
				while ( rs1.next() ) {
					tempeid = rs1.getInt("eid");
					tempgrade = rs1.getInt("grade");
					String query2 = "select evaluationType, weightage from Evaluation where eid = "+tempeid;
					Statement stmt2 = con.createStatement();
					ResultSet rs2 = stmt2.executeQuery( query2 );
					while ( rs2.next() ) {
						temptype = rs2.getString( "evaluationType" );
						tempweightage = rs2.getInt("weightage");
					}
					String query3 = "select sum(grade) as totalgrade from Grade where cid ="+tempcid +" and sid = "+selectsid;
					Statement stmt3 = con.createStatement();
					ResultSet rs3 = stmt2.executeQuery( query3 );
					while ( rs3.next() ) {
						 total = rs3.getInt( "totalgrade" );
						 textarea.appendText(tempcname +"     	    "+tempeid+"          "+
									temptype+"        "+ tempgrade+"          "+tempweightage+"         "+total+"\n");
					}
					
					rs2.close();
					stmt2.close();
					rs3.close();
					stmt3.close();
				}
				
				rs1.close();
				stmt1.close();
				
			}
			rs.close();
			stmt.close();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	@FXML
	private void handleMyCourse(ActionEvent event){
		System.out.println("Display my courses "+selectsid);
		textarea.clear();
		String query = "select cname, cid from Assign where sid = "+ selectsid;
		try {
			Statement stmt = con.createStatement();
			textarea.appendText("CourseName     Semester	Year	meetAt	Room \n");
			ResultSet rs = stmt.executeQuery( query );
			while ( rs.next() ) {
				String tempcname = rs.getString( "cname" );
				int tempcid = Integer.parseInt(rs.getString( "cid" ));
				query = "select cname, semester, year, meetat, room from Course where cid="+tempcid;
				Statement stmt1 = con.createStatement();
				ResultSet rs1 = stmt1.executeQuery( query );
				while ( rs1.next() ) {
					String cname = rs1.getString( "cname" );
					String csemester = rs1.getString( "semester" );
					String cyear = rs1.getString( "year" );
					String cmeetat = rs1.getString( "meetat" );
					String croom = rs1.getString( "room" );
					textarea.appendText(cname +"		"+csemester+"	"+
										cyear+"		"+ cmeetat+"	"+croom+"\n");
				}
				rs1.close();
				stmt1.close();
			}
			rs.close();
			stmt.close();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleAssign(ActionEvent event){
		setVisibilityInGeneral(false);
		ArrayList<String> result = sdb.getStudent();
    	schoice.setItems(FXCollections.observableArrayList(result));
		schoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(newValue.intValue() >=0){
					 selectsid = Integer.parseInt(result.get(newValue.intValue()));
					 System.out.println(selectsid);
				}
			}
		});
		ArrayList<String> cresult = sdb.getCourse();
    	cchoice.setItems(FXCollections.observableArrayList(cresult));
		cchoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(newValue.intValue() >=0){
					 selectcid = Integer.parseInt(cresult.get(newValue.intValue()));
					 System.out.println(selectcid + " "+selectsid);
				}
			}
		});
		setAssignVisibility(true);
		System.out.println("here course: "+ selectcid + "  student: "+ selectsid);
	}
	@FXML
	private void ConfirmAssignStudent(ActionEvent event){
		System.out.println("course: "+ selectcid + "  student: "+ selectsid);
		String tempname = "";
		String query = "insert into Assign " + 
				" (cname,sid,cid)" 
				+ " values (?,?,?)";
		String subquery = "Select cname from Course where cid = "+selectcid;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery( subquery );
			while ( rs.next() ) {
				tempname = rs.getString( "cname" );
			}
			rs.close();
			stmt.close();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, tempname);
			pstmt.setInt(2, selectsid);
		    pstmt.setInt(3,selectcid);
		    pstmt.executeUpdate();
			pstmt.close();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}	
	}
	@FXML
	private void handleModifyCourse(ActionEvent event){
		setVisibilityInGeneral(false);
		setModifyCourseVisibility(true);
	}

	@FXML
	private void handleModifyEvaluation(ActionEvent event){
		setVisibilityInGeneral(false);
		setCreateEvaVisibility(true);
    	setModifyEvaVisibility(true);
	}
	
	@FXML
    private void handleRequiredEvaID(ActionEvent event){
		evaID = Integer.parseInt(tevaid.getText());
	}
	@FXML
	private void handleUpdateDueDate(ActionEvent event){
		String query = "Update Evaluation "+
						"SET duedate = ? where eid = ? and fid = ?";
		try {
			PreparedStatement update = con.prepareStatement(query);
			Timestamp timestamp = new Timestamp(duedate.getTime());
		    update.setTimestamp(1, timestamp);
			update.setInt(2, evaID);
			update.setInt(3, selectfid);
			update.executeUpdate();
			con.commit();
			update.close();
		}
		catch ( SQLException e ) {System.out.println(e.getMessage());}
	}
	@FXML
	private void updateMeetatEva(ActionEvent event){
		String query = "Update Evaluation "+
						"SET meetingroom = ? where eid = ? and fid = ?";
		try {
			PreparedStatement update = con.prepareStatement(query);
			update.setString(1, evameetat);
			update.setInt(2, evaID);
			update.setInt(3, selectfid);
			update.executeUpdate();
			con.commit();
			update.close();
		}
		catch ( SQLException e ) {System.out.println(e.getMessage());}
	}
	@FXML
	private void handleUpdateEvaType(ActionEvent event){;
		String query = "Update Evaluation "+
						"SET evaluationType = ? where eid = ? and fid = ?";
		try {
			PreparedStatement update = con.prepareStatement(query);
			update.setString(1, type);
			update.setInt(2, evaID);
			update.setInt(3, selectfid);
			update.executeUpdate();
			con.commit();
			update.close();
		}
		catch ( SQLException e ) {System.out.println(e.getMessage());}
	}
	@FXML
	private void updateWeightage(ActionEvent event){
		String query = "Update Evaluation "+
						"SET weightage = ? where eid = ? and fid = ?";
		try {
			PreparedStatement update = con.prepareStatement(query);
			update.setInt(1, weightage);
			update.setInt(2, evaID);
			update.setInt(2, selectfid);
			update.executeUpdate();
			con.commit();
			update.close();
		}
		catch ( SQLException e ) {System.out.println(e.getMessage());}
	}

	@FXML
	private void handleCreateEvaluation(ActionEvent event){
	    setVisibilityInGeneral(false);
    	    setCreateModifyEvaVisibility(true);
	}

	@FXML
	private void handleEnterGrade(ActionEvent event){
		setVisibilityInGeneral(false);
		ArrayList<String> result = sdb.getStudent();
		stuevagrade.setItems(FXCollections.observableArrayList(result));
		stuevagrade.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(newValue.intValue() >=0){
					 selectsid = Integer.parseInt(result.get(newValue.intValue()));
				}
			}
		});
		ArrayList<String> cresult = sdb.getEvaluation();
		evachoicegrade.setItems(FXCollections.observableArrayList(cresult));
		evachoicegrade.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(newValue.intValue() >=0){
					 selecteid = Integer.parseInt(cresult.get(newValue.intValue()));
					 System.out.println(selecteid + " "+selectsid);
				}
			}
		});
		setGradeVisibility(true);
	}
    @FXML
	private void handleNewGrade(ActionEvent event){
    	onegrade = Integer.parseInt(newgrade.getText());
	}
    @FXML
	private void ConfirmAssignGrade(ActionEvent event){
    	System.out.println("eva: "+selecteid+ "; sid: "+selectsid+" "+ "; cid: "+selectcid+onegrade );
		String query = "insert into Grade " + 
				" (eid,sid,cid,grade)" 
				+ " values (?,?,?,?)";
		int tempcid = 0;
		try {
			String query1 = "select cid from Evaluation where eid = "+ selecteid;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery( query1 );
			while ( rs.next() ) {
				tempcid = rs.getInt( "cid" );
			}
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, selecteid);
		    pstmt.setInt(2,selectsid);
		    pstmt.setInt(3,tempcid);
		    pstmt.setInt(4,onegrade);
		    pstmt.executeUpdate();
			pstmt.close();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}	
	}
	@FXML
	private void handleReportClass(ActionEvent event){
		setVisibilityInGeneral(false);
		System.out.println("Display all reports ");
		int countstu = 0;
		int counteva = 0;
		textarea.clear();
		String query = "select cid, cname, meetat, room from Course where fid = "+ selectfid;
		try {
			Statement stmt = con.createStatement();
			textarea.appendText("CourseName     MeetAt		RoomNo	#Students    #Evaluations \n");
			ResultSet rs = stmt.executeQuery( query );
			while ( rs.next() ) {
				String tempcname = rs.getString( "cname" );
				int tempcid = Integer.parseInt(rs.getString( "cid" ));
				String tempmeetat = rs.getString( "meetat" );
				String temproom = rs.getString( "room" );
				query = "select count(*) as total from (select distinct sid from Assign where cid ="+tempcid+")";
				Statement stmt1 = con.createStatement();
				ResultSet rs1 = stmt1.executeQuery( query );
				while ( rs1.next() ) {
					countstu = rs1.getInt( "total" );
				}
				String query2 = "select count(*) as totaleva from (select distinct eid from Evaluation where cid ="+tempcid+")";
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery( query2 );
				while ( rs2.next() ) {
					counteva = rs2.getInt( "totaleva" );
					textarea.appendText(tempcname +"     	"+tempmeetat+"     	"+
										temproom+"    	   "+ countstu+"      	  "+counteva+"\n");
				}
				rs1.close();
				stmt1.close();
				rs2.close();
				stmt2.close();
			}
			rs.close();
			stmt.close();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleReportEva(ActionEvent event){
		setVisibilityInGeneral(false);
		System.out.println("Display all reports ");
		int sid = 0;
		String stuname ="";
		int total = 0;
		textarea.clear();
		String query = "select cid, cname, year, semester from Course where fid = "+ selectfid;
		try {
			Statement stmt = con.createStatement();
			textarea.appendText("CourseName     Semester		Year	  StudentName    CurrentGrade\n");
			ResultSet rs = stmt.executeQuery( query );
			while ( rs.next() ) {
				String tempcname = rs.getString( "cname" );
				int tempcid = Integer.parseInt(rs.getString( "cid" ));
				String tempsem = rs.getString( "semester" );
				int tempyear = rs.getInt( "year" );
				query = "select sid from Assign where cid ="+tempcid;
				Statement stmt1 = con.createStatement();
				ResultSet rs1 = stmt1.executeQuery( query );
				while ( rs1.next() ) {
					sid = rs1.getInt("sid");
					String query2 = "select sname from Student where sid="+sid;
					Statement stmt2 = con.createStatement();
					ResultSet rs2 = stmt2.executeQuery( query2 );
					while ( rs2.next() ) {
						stuname = rs2.getString( "sname" );
					}
					
					String query3 = "select sum(grade) as totalgrade from Grade where cid ="+tempcid +" and sid = "+sid;
					Statement stmt3 = con.createStatement();
					ResultSet rs3 = stmt2.executeQuery( query3 );
					while ( rs3.next() ) {
						 total = rs3.getInt( "totalgrade" );
						 textarea.appendText(tempcname +"     	"+tempsem+"           "+
									tempyear+"    	   "+ stuname+"      	  "+total+"\n");
					}
					
					rs2.close();
					stmt2.close();
					rs3.close();
					stmt3.close();
				}
				
				rs1.close();
				stmt1.close();
				
			}
			rs.close();
			stmt.close();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	private void initialize() {
		
	}
	
	public void setNull(){
		newcname =null;
		newmeetsat = null;
		semester = null;
		newroom = null;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("CourseManageSystem");
        initRootLayout();
		
	}
	
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(getClass().getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            //scene.getStylesheets().add("application.css");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showOverview();
    }


    public void showOverview() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Overview.fxml"));
            AnchorPane overview = (AnchorPane) loader.load();
            rootLayout.setCenter(overview);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

	/* Main() */
	public static void main( String [] args ) {
		 sdb = new MainUI();
		launch(args);
	}
}

