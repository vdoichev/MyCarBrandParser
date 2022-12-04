package com.vdoichev.db;

import com.vdoichev.objects.Mark;

import java.sql.*;

public class MyDbForAbp {
    public boolean addMark(Mark mark){
        try (Connection con = MySqlConnection.getConnection()) {
            String sql = "INSERT INTO mark(name) values (?)";
            PreparedStatement statement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, mark.getName());
            int result = statement.executeUpdate();
            if (result > 0) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    mark.setId(id);
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
