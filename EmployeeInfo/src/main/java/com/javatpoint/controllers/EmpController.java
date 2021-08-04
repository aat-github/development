package com.javatpoint.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.javatpoint.beans.Emp;
import com.javatpoint.dao.EmpDao;

/**
 * 作成者 : エエティン
 * クラス名 : EmpController 
 * 概要 　　: 社員情報コントローラ
 * 作成日　:　2021/08/02
 */

@Controller
public class EmpController {
	/**
	 * xmlファイルからdaoを挿入する
	 */
	@Autowired
	EmpDao dao;
	
	/**
	 * ログイン画面表示処理
	 * @param m　社員情報モデル
	 * @return　ログイン画面へ遷移する
	 */
	@RequestMapping(value="/loginform")
    public String login(Model m) {    
		m.addAttribute("command", new Emp());
        return "login";    
    }      
	
	/**
	 * ログイン処理
	 * @param emp 社員情報モデル
	 * @param m　社員情報モデル
	 * @return　 ユーザ情報画面へ 遷移する
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("emp") Emp emp, Model m) {
		//　　データベース(UserLoginテーブル)情報を取得
		boolean userFlag = dao.userLogin(emp);
		// ログイン判定
		if(userFlag) {
			Emp userInfo = dao.getEmpById(emp.getId());
			System.out.println(userInfo.getBirthday());
			String password = dao.getUserPassword(emp.getId());
	        userInfo.setPassword(password);
			m.addAttribute("command",userInfo);
			// ユーザ情報画面へ 
			return "user_info";
		}else {
			String errorMsg = "ログインに失敗しました。";
			m.addAttribute("command", emp);
			m.addAttribute("errorMsg", errorMsg);
			return "login";
		}
	}
	
	/**
	 * ユーザ情報更新処理
	 * @param emp　社員情報モデル
	 * @return　
	 */
	@RequestMapping(value = "/userInfoEdit", method = RequestMethod.POST)
	public String userInfoEdit(@ModelAttribute("emp") Emp emp, Model m) {
		dao.userInfoUpdate(emp);
		Emp userInfo = dao.getEmpById(emp.getId());
		m.addAttribute("command",userInfo);
		// ユーザ情報画面へ 
		return "user_info";
	}

	/**
	 * 社員情報登録画面表示処理
	 * @param m 社員情報モデル
	 * @return　社員情報登録画面へ遷移する
	 */
	@RequestMapping("/empform")
	public String showform(Model m) {
		m.addAttribute("command", new Emp());
		return "empform";
	}

	/**
	 * 社員情報登録処理
	 * @param emp 社員情報
	 * @return　社員情報画面へ遷移する
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("emp") Emp emp) {
		dao.saveEmployee(emp);
		dao.saveUser(emp);
		return "redirect:/viewemp";
	}

	/* It provides list of employees in model object */
	@RequestMapping("/viewemp")
	public String viewemp(Model m) {
		List<Emp> list = dao.getEmployees();
		m.addAttribute("list", list);
		return "viewemp";
	}

	/*
	 * It displays object data into form for the given id. The @PathVariable puts
	 * URL data into variable.
	 */
	@RequestMapping(value = "/editemp/{id}")
	public String edit(@PathVariable int id, Model m) {
		Emp emp = dao.getEmpById(id);
		m.addAttribute("command", emp);
		return "empeditform";
	}
	
	

	/* It updates model object. */
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("emp") Emp emp) {
		dao.update(emp);
		return "redirect:/viewemp";
	}

	/* It deletes record for the given id in URL and redirects to /viewemp */
	@RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		dao.delete(id);
		return "redirect:/viewemp";
	}
}