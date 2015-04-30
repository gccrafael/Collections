
package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author Rafael Garcia
 */
public class EmpIO {
    public static Map<Long,Employee> getEmps(String path) {
        Map<Long, Employee> elist = new HashMap<>();
        
        try {
            BufferedReader in = new BufferedReader(
                               new FileReader(path));
            in.readLine(); //discard column headings
            String e = in.readLine();
            while (e != null) {
                String[] edata = e.split(",");
                Employee emp = new Employee();
                long empno = Long.parseLong(edata[0]);
                emp.setEmpno(empno);
                if (!edata[1].isEmpty()) {emp.setFirstnm(edata[1]);}
                if (!edata[2].isEmpty()) {emp.setLastnm(edata[2]);}
                if (!edata[3].isEmpty()) {emp.setMidnm(edata[3]);}
                if (!edata[4].isEmpty()) {emp.setSuffix(edata[4]);}
                if (!edata[5].isEmpty()) {emp.setAddr1(edata[5]);}
                if (!edata[6].isEmpty()) {emp.setAddr2(edata[6]);}
                if (!edata[7].isEmpty()) {emp.setCity(edata[7]);}
                if (!edata[8].isEmpty()) {emp.setState(edata[8]);}
                if (!edata[9].isEmpty()) {emp.setZip(edata[9]);}
                if (!edata[10].isEmpty()) {emp.setPhone(Integer.parseInt(edata[10]));}
                if (!edata[11].isEmpty()) {emp.setGender(edata[11]);}
                if (!edata[12].isEmpty()) {emp.setStatus(edata[12]);}
                if (!edata[13].isEmpty()) {emp.setHiredt(edata[13]);}
                if (!edata[14].isEmpty()) {emp.setTerminatedt(edata[14]);}
                if (!edata[15].isEmpty()) {emp.setPaycd(Integer.parseInt(edata[15]));}
                elist.put(empno, emp);
                e = in.readLine();                
            }//end of while
            in.close();
        } catch (Exception e) {
            //no action: just return map as is
        }
        return elist;
    }
    public static String setEmps(String path, Map<Long,Employee> mp) {
        String status = "";
        
        
        try {
            Iterator<Map.Entry<Long,Employee>> it = mp.entrySet().iterator();
            PrintWriter out = new PrintWriter(new FileWriter(path));
            boolean index = true;
            while(it.hasNext()) {
                Map.Entry<Long,Employee> empentry = it.next();                
                if (index) {
                out.println("Empno" + "," + "Firstname" + "," +
                        "Lastname" + "," + "MiddleInit" + "," +
                        "Suffix" + "," + "Address1" + "," +
                        "Address2" + "," + "City" + "," +
                        "State" + "," + "Zip" + "," +
                        "Phone" + "," + "Gender" + "," +
                        "Status" + "," + "HireDate" + "," +
                        "TerminateDt" + "," + "PayCd");
                index = false;
                }
                out.println(empentry.getValue().toString());
            }
            out.close();
            status = "Data valid in: " + path;
        } catch (Exception e) {
            
            status = "Error = " + e.getMessage();
        }
        return status;
    }    
}
