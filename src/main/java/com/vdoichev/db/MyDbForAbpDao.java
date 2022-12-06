package com.vdoichev.db;

import com.vdoichev.utils.impl.MarkParser;
import com.vdoichev.utils.impl.MarketParser;
import com.vdoichev.utils.impl.ModelParser;

import java.sql.*;
import java.util.Objects;

public class MyDbForAbpDao {

    public static final String SQL_INSERT_FOR_MODEL =
            "INSERT INTO model(name, code, date_from, mark_id, market_id) " +
            "values (?, ?, ?, ?, ?)";
    public static final String SELECT_ID_FROM_MODEL =
            "select id from model where code = ? and mark_id = ? and market_id = ?";
    public static final String SELECT_ID_FROM_MARKET =
            "select id from market where code = ?";
    public static final String SELECT_ID_FROM_MARK = "select id from mark where name = ?";
    public static final String INSERT_INTO_MARK = "INSERT INTO mark(name, catalogGroup) values (?, ?)";
    public static final String INSERT_INTO_MARKET = "INSERT INTO market(code, name) values (?, ?)";

    public static int getIdByMark(MarkParser markParser) {
        try (Connection con = MySqlConnection.getConnection()) {
            System.out.print("Перевірка наявності в БД марки авто " +
                    markParser.getName());
            PreparedStatement statement =
                    Objects.requireNonNull(con).prepareStatement(SELECT_ID_FROM_MARK);
            statement.setString(1, markParser.getName());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println(" - знайдено запис id = " + rs.getInt("id"));
                return rs.getInt("id");
            } else System.out.println(" - не знайдено");
        } catch (SQLException ex) {
            System.out.println("Виникла помилка при перевірці наявності в БД марки авто " +
                    markParser.getName());
        }
        return 0;
    }

    public static boolean addMark(MarkParser markParser) {
        try (Connection con = MySqlConnection.getConnection()) {
            PreparedStatement statement =
                    Objects.requireNonNull(con).prepareStatement(INSERT_INTO_MARK,
                            Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, markParser.getName());
            statement.setString(2, markParser.getCatalogGroup());
            int resultUpdate = statement.executeUpdate();
            if (resultUpdate > 0) {
                System.out.println("Успішне додавання марки авто " +
                        markParser.getName() + "!");
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    markParser.setId(id);
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Виникла помилка при додаванні в БД марки авто " +
                    markParser.getName());
        }
        return false;
    }

    public static int getIdByMarket(MarketParser marketParser) {
        try (Connection con = MySqlConnection.getConnection()) {
            System.out.print("Перевірка наявності в БД марки авто " +
                    marketParser.getName());
            PreparedStatement statement =
                    Objects.requireNonNull(con).prepareStatement(SELECT_ID_FROM_MARKET);
            statement.setString(1, marketParser.getCode());
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                System.out.println(" - знайдено запис id = " + rs.getInt("id"));
                return rs.getInt("id");
            } else System.out.println(" - не знайдено");
        } catch (SQLException ex) {
            System.out.println("Виникла помилка при перевірці наявності в БД марки авто " +
                    marketParser.getName());
        }
        return 0;
    }

    public static boolean addMarket(MarketParser marketParser) {
        try (Connection con = MySqlConnection.getConnection()) {
            PreparedStatement statement =
                    Objects.requireNonNull(con).prepareStatement(INSERT_INTO_MARKET,
                            Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, marketParser.getCode());
            statement.setString(2, marketParser.getName());
            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("Успішне додавання ринку збуту " +
                        marketParser.getName() + "!");
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    marketParser.setId(id);
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Виникла помилка при додаванні в БД ринку збуту " +
                    marketParser.getName());
        }
        return false;
    }

    public static int getIdByModel(ModelParser modelParser, Integer mark_id, Integer market_id) {
        try (Connection con = MySqlConnection.getConnection()) {
            System.out.print("Перевірка наявності в БД моделі авто " +
                    modelParser.getName());
            PreparedStatement statement =
                    Objects.requireNonNull(con).prepareStatement(SELECT_ID_FROM_MODEL);
            statement.setString(1, modelParser.getCode());
            statement.setString(2, mark_id.toString());
            statement.setString(3, market_id.toString());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println(" - знайдено запис id = " + rs.getInt("id"));
                return rs.getInt("id");
            } else System.out.println(" - не знайдено");
        } catch (SQLException ex) {
            System.out.println("Виникла помилка при перевірці наявності в БД моделі авто " +
                    modelParser.getName());
        }
        return 0;
    }

    public static boolean addModel(ModelParser modelParser, Integer mark_id, Integer market_id) {
        try (Connection con = MySqlConnection.getConnection()) {
            PreparedStatement statement =
                    Objects.requireNonNull(con).prepareStatement(SQL_INSERT_FOR_MODEL,
                        Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, modelParser.getName());
            statement.setString(2, modelParser.getCode());
            statement.setString(3, modelParser.getProductionDateFormat().toString());
            statement.setString(4, mark_id.toString());
            statement.setString(5, market_id.toString());
            int resultUpdate = statement.executeUpdate();
            if (resultUpdate > 0) {
                System.out.println("Успішне додавання моделі авто " +
                        modelParser.getName() + "!");
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    modelParser.setId(id);
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Виникла помилка при додаванні в БД моделі авто " +
                    modelParser.getName());
            ex.printStackTrace();
        }
        return false;
    }
}
