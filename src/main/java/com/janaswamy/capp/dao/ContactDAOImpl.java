/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.janaswamy.capp.dao;

import com.janaswamy.capp.domain.Contact;
import com.janaswamy.capp.domain.User;
import com.janaswamy.capp.rm.ContactRowMapper;
import com.janaswamy.capp.rm.UserRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


 /*
 * @author janaswamya
 */

@Repository
public class ContactDAOImpl extends BaseDAO implements ContactDAO{

    @Override
    public void save(Contact c) {
        String sql="INSERT INTO contact(userId, name, phone, email, address, remark)"
                +" VALUES(:userId, :name, :phone, :email, :address, :remark)";
        
        Map m = new HashMap();
        m.put("userId", c.getUserId());
        m.put("name",c.getName());
        m.put("phone",c.getPhone());
        m.put("email",c.getEmail());
        m.put("address",c.getAddress());
        m.put("remark",c.getRemark());
        SqlParameterSource ps = new MapSqlParameterSource(m);
        KeyHolder kh = new GeneratedKeyHolder();
        getNamedParameterJdbcTemplate().update(sql, ps, kh);
        c.setContactId(kh.getKey().intValue());
    }

    @Override
    public void update(Contact c) {
         String sql="UPDATE contact SET"
                 +" name=:name,"
                 +" phone=:phone,"
                 +" email=:email,"
                 +" address=:address,"
                 +" remark=:remark "
                 +" WHERE contactId=:contactId";
          
        Map m = new HashMap();
        m.put("contactId", c.getContactId());
        m.put("name",c.getName());
        m.put("phone",c.getPhone());
        m.put("email",c.getEmail());
        m.put("address",c.getAddress());
        m.put("remark", c.getRemark());
        getNamedParameterJdbcTemplate().update(sql, m);
        
    }

    @Override
    public void delete(Contact c) {
        this.delete(c.getContactId());
    }

    @Override
    public void delete(Integer contactId) {
        String sql="DELETE FROM contact WHERE contactId=?";
        getJdbcTemplate().update(sql, contactId);
      
    }

   
    
    @Override
    public List<Contact> findAll() {
        String sql = "SELECT contactId, userId, name, phone, email, address, remark"
                    +" FROM contact ";
        return getJdbcTemplate().query(sql, new ContactRowMapper());
        }

    @Override
    public List<Contact> findByProperty(String propName, Object propValue) {
         String sql = "SELECT contactId, userId, name, phone, email, address, remark"
                    +" FROM contact WHERE "+propName+"=?";
        return getJdbcTemplate().query(sql, new ContactRowMapper(), propValue); 
    }

    @Override
    public Contact findById(Integer contactId) {
        String sql = "SELECT contactId, userId, name, phone, email, address, remark"
                    +" FROM contact WHERE contactId=?";
        return getJdbcTemplate().queryForObject(sql, new ContactRowMapper(), contactId);
    }
}
