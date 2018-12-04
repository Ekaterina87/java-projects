import javax.swing.JOptionPane;

class Student{
	
int status;
/*  0,1 - NYS resident : 0- Matriculated, 1- Non-matriculated;
 * - 2,3,4- non-Resident NYS: 2-Out of state resident, 3-Foreign, 4-Non-Matriculated   */ 
String name1, name2;
Student(){
	int i,n;
	String r;
	name1 = JOptionPane.showInputDialog("Enter your first name:");
	name2 = JOptionPane.showInputDialog("Enter your last name:");
	r = JOptionPane.showInputDialog("Are you NYS resident?\n" + 
            "Enter 1 (Yes) or 2 (No)");
	 n = Integer.parseInt(r);
	 
	 
	 switch (n) {
	 case 1: //  NY resident
	 r = JOptionPane.showInputDialog("Are you non-matriculated student?\n" + 
             "Enter 1 (Yes) or 2 (No)");
	 i = Integer.parseInt(r);
	 
	if(i==1) 
	status=1;
	else
		status=0;
	break;
	 case 2:  // Not NYS-resident
		
		r = JOptionPane.showInputDialog("Are you foreign student?\n" + 
	            "Enter 1 (Yes) or 2 (No)");
		 i = Integer.parseInt(r);
		 
		 if(i==1) 
				status=3;
		 else
				status=2;
		 
		 r = JOptionPane.showInputDialog("Are you non-matriculated student?\n" + 
                 "Enter 1 (Yes) or 2 (No)");

i = Integer.parseInt(r);
if(i==1) 
	status=4;
	break;	
	}
	
	
}
}

class Department{
	int num; // 0-Social Science, 1-Business, 2- Music, 3- Engineering Technology
	
	String deps[]= {"Social Science", "Business", "Music", "Engineering Technology"};
	String courses[][]= {{"Sociology", "Antropology", "Philosophy"},
			             {"Finance", "Accounting", "Business Administration"},
			             {"Jazz", "Songwriting", "Perfomance"},
			             {"Electronics", "Robotics", "Civil engineering"}};
	
	Department(){
		int i, n=deps.length;
		String s, r;
		s= "Select one of Departments";
		for (i=0; i<n; i++)
			s= s+ "\n" + (i+1)+ " - "+ deps[i];
		r= JOptionPane.showInputDialog(s);
		num= Integer.parseInt(r) - 1; 
	}
		String getName() {
			return deps[num];
		}
		
	
	}

class Tuition{
	int st, // st-status of Student
	dep, // dep- department number
	maj; // maj - course number
	
	int majors, electives, credits, time; // time: Full time -0, Part time-1
	String majName;
	double c [][]= {{2400, 265, 320, 320, 420},
			{210, 265, 320, 320,420}};
	
	Tuition(Student s, Department d){
		int i,n;
		String r, u;
	
		st = s.status; 
		dep= d.num;
		n= d.courses[dep].length;
		r = "Select one major from the list";
		for( i=0; i<n; i++)
			r= r + "\n" + (i+1) + " - " + d.courses[dep][i];
		u= JOptionPane.showInputDialog(r);
		maj= Integer.parseInt(u) - 1; 
		majName = d.courses[dep][maj];
		r= JOptionPane.showInputDialog(" How many major courses do you take?");
		majors= Integer.parseInt(r);
				
		r= JOptionPane.showInputDialog(" How many elective courses do you take?");
		electives= Integer.parseInt(r);
		credits= getCredits();
		time = getTime();
				
	}
	int getCredits() {
		int n = majors*3 + electives*2;
		return n;
		
	}
	int getTime() {
		int n;
		if (credits< 12)
			n=1;
		else 
			n=0;
		return n;
		}
	
	double fee() {
		double s;
			 
			if( st==0 && time==0)
				s=c[0][0];
			else s= c[time][st]*credits;
			return s;
		}
	}

class FixedFee{
	int d, t; //d- number of Department, t- time full or part
	double c1[]= {12,13,15,11};
	double c2[]= {125,62.5};
	double c3[]= {62.85,16.83};
	
	FixedFee(Tuition tt){
	d= tt.dep;
	t= tt.time;
	
	}
	double fee1() { // consolidated fee
		return c1[d];
	}
	double fee2() { // technology fee
		return c2[t];
		
	}
	double fee3() { // student activity fee
		return c3[t];
	}
}
public class QccProjectRomenkova{
public static void main (String args[])
{ 
	double cost, c1, c2, c3, total;
	String r;
	int n;
	Student s; Department d; Tuition t; FixedFee f;
	boolean newRun=true;
	s= new Student();
	while (newRun) {
		
	
	d= new Department();
	t= new Tuition(s,d);
	f= new FixedFee(t);
	cost= t.fee();
	c1= f.fee1();
	c2= f.fee2();
	c3= f.fee3();
	total= cost + c1 +c2+ c3;
	r= "Thank you " + s.name1 + " "+ s.name2+
			" for choosing the department of " + d.getName();
	r= r + "\nThe Total tuition and fee for the major in " + t.majName+ " is $"+ total;
	r= r + "\nThe break of the tuition is:\nCost for the major and elective courses is $" + cost;
	r=r+"\nConsolidated fee for major is $"+c1;
	r=r+"\nTechnology fee $"+c2;
	r=r+"\nStudent activity fee $"+c3;
	r=r+"\nTotal Tuition and fee cost $"+ total;
	JOptionPane.showMessageDialog(null, r, "Result",JOptionPane.PLAIN_MESSAGE);
	r= JOptionPane.showInputDialog(" do you want to calculate another tuition fee?\n"+
	"Enter 1 (Yes) or 2 (No)");
	n= Integer.parseInt(r);
	newRun = (n==1); 
	}
	
	
}
}