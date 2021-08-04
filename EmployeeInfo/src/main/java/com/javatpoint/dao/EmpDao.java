package com.javatpoint.dao;  
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;  
import org.springframework.jdbc.core.BeanPropertyRowMapper;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.RowMapper;  
import com.javatpoint.beans.Emp;
  
/**
 * 作成者 : エエティン
 * クラス名 : EmpDao 
 * 概要 　　: 社員情報 DAO
 * 作成日　:　2021/08/02
 */

public class EmpDao {  
JdbcTemplate template;  
  
public void setTemplate(JdbcTemplate template) {  
    this.template = template;  
}  

/**
 * EmployeeテーブルにInsert文
 * @param p  社員情報モデル
 */
public int saveEmployee(Emp p){  
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    String sql="insert into employee(id,name,age,gender,birthday,tel,address,category,joined_day,admin_auth) values('"
              + p.getId()+"','"
    		  +p.getName()+"','"
              + p.getAge()+"','"
    		  + p.getGender()+"','"
    		  + sdf.format(p.getBirthday())+"','"
    		  + p.getTel()+"','"
    	      + p.getAddress()+"','" 
    	      + p.getCategory()+"','"
    	      + sdf.format(p.getJoined_day())+"','" 
    	      + p.getAdmin_auth()+"')";  
    return template.update(sql); 
}  

/**
 * UseLoginテーブルにInsert文
 * @param p  社員情報モデル
 */
public int saveUser(Emp p){  
    String sql="insert into uselogin(id,password) values('"
              +p.getId()+"',"
    	      +p.getPassword()+")";  
    return template.update(sql); 
}  

/**
 * 
 * @param p 
 * @return
 */
public int userInfoUpdate(Emp p){  
    String sql="update uselogin set password='"+p.getPassword()+"' where id="+p.getId()+"";  
    return template.update(sql);  
}  

public int update(Emp p){  
    String sql="update Emp99 set name='"+p.getName()+"', salary="+p.getSalary()+",designation='"+p.getDesignation()+"' where id="+p.getId()+"";  
    return template.update(sql);  
}  
public int delete(int id){  
    String sql="delete from Emp99 where id="+id+"";  
    return template.update(sql);  
}  

/**
 * データベース(UseLoginテーブル)情報を取得する
 * @param p　社員情報モデル
 * @return　boolean レコードが存在しているかどうかのフラグ
 */
public boolean userLogin(Emp p){
	 String sql="select count(*) from uselogin where id= ? and password=?"; 
	 int count=  template.queryForObject(sql, new Object[]{p.getId(),p.getPassword()},Integer.class); 
	 return count > 0 ;
} 

public List<Emp> getEmployees(){  
    return template.query("select * from Emp99",new RowMapper<Emp>(){  
        public Emp mapRow(ResultSet rs, int row) throws SQLException {  
            Emp e=new Emp();  
            e.setId(rs.getInt(1));  
            e.setName(rs.getString(2));  
            e.setSalary(rs.getFloat(3));  
            e.setDesignation(rs.getString(4));  
            return e;  
        }  
    });  
}

public Emp getEmpById(int id){  
    String sql="select * from employee where id=?";  
    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Emp>(Emp.class));  
}

public String getUserPassword(int id){  
    String sql="select password from uselogin where id=?";  
    return template.queryForObject(sql, new Object[]{id},String.class);  
}

}  