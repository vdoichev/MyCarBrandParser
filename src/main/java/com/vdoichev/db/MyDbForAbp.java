package com.vdoichev.db;

import com.vdoichev.objects.Market;
import com.vdoichev.utils.impl.MarkParser;
import com.vdoichev.utils.impl.MarketParser;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class MyDbForAbp {
    public static void saveObjectToDB(List<MarkParser> marks) {
        for (MarkParser mark : marks) {
            if (addMark(mark)) {
                for (MarketParser market : mark.getMarkets()) {
                    if (addMarket(market)) {

                    } else {
                        System.out.println("Сталася помилка при збереженні ринку збуту " +
                                market.getName() + " для марки авто +" + mark.getName());
                    }
                }
            } else {
                System.out.println("Сталася помилка при збереженні марки авто +" + mark.getName());
            }
        }
    }

    public static boolean addMark(MarkParser markParser) {
        try (Connection con = MySqlConnection.getConnection()) {
            String sql = "INSERT INTO mark(name, catalogGroup) values (?, ?)";
            PreparedStatement statement = Objects.requireNonNull(con).prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, markParser.getName());
            statement.setString(2, markParser.getCatalogGroup());
            int resultUpdate = statement.executeUpdate();
            if (resultUpdate > 0) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    markParser.setId(id);
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean addMarket(Market market) {
        try (Connection con = MySqlConnection.getConnection()) {
            String sql = "INSERT INTO market(code, name) values (?, ?)";
            PreparedStatement statement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, market.getCode());
            statement.setString(2, market.getName());
            int result = statement.executeUpdate();
            if (result > 0) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    market.setId(id);
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
