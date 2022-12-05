package com.vdoichev.db;

import com.vdoichev.objects.Market;
import com.vdoichev.utils.impl.MarkParser;
import com.vdoichev.utils.impl.MarketParser;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class MyDbForAbp {
    public static void saveMarksToDB(List<MarkParser> marks) {
        for (MarkParser mark : marks) {
            mark.setId(getIdByMark(mark));
            if (mark.getId() > 0 || addMark(mark)) {
                System.out.println("Додавання марки авто " +
                        mark.getName() + "!");
                saveMarketsToDB(mark.getMarkets());
            } else {
                System.out.println("Сталася помилка при збереженні марки авто " +
                        mark.getName() + "!");
            }
        }
    }

    public static void saveMarketsToDB(List<MarketParser> markets) {
        for (MarketParser market : markets) {
            if (!market.isEmpty()) {
                market.setId(getIdByMarket(market));
                if (market.getId() > 0 || addMarket(market)) {
                    System.out.println("Додавання ринку збуту " +
                            market.getName() + "!");
                } else {
                    System.out.println("Сталася помилка при збереженні ринку збуту " +
                            market.getName() + " або він вже існує!");
                }
            }
        }
    }

    private static int getIdByMarket(MarketParser marketParser) {
        try (Connection con = MySqlConnection.getConnection()) {
            String sql = "select id from market where code = ?";
            PreparedStatement statement = Objects.requireNonNull(con).prepareStatement(sql);
            statement.setString(1, marketParser.getCode());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static int getIdByMark(MarkParser markParser) {
        try (Connection con = MySqlConnection.getConnection()) {
            String sql = "select id from mark where name = ?";
            PreparedStatement statement = Objects.requireNonNull(con).prepareStatement(sql);
            statement.setString(1, markParser.getName());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
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
            PreparedStatement statement = Objects.requireNonNull(con).prepareStatement(sql,
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
