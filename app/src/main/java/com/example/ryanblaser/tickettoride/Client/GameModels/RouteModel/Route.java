package com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel;

import android.graphics.Color;
import android.graphics.PointF;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benjamin on 4/03/17.
 */

public class Route {

    public Route(int color, int weight, String owner, Pair<String, String> p, PointF p1, PointF p2){
        _i_Color = color;
        _i_Weight = weight;
        _S_Owner = owner;
        _P_ConnectingCities = p;
        point1 = p1;
        point2 = p2;
    }

    //---------------------------------------CLASS VARIABLES-------------------------------------//
    private int _i_Color;
    private int _i_Weight = 0;
    private String _S_Owner = null;
    private Pair<String, String> _P_ConnectingCities = null;

    private PointF point1;
    private PointF point2;

    //---------------------------------------STATIC VARIABLES------------------------------------//
    private static Boolean _B_areRoutesSet = false;
    private static List<Route> _L_allRoutes = new ArrayList<>();
    private static List<Route> _L_availableRoutes = new ArrayList<>();
    private static Map<String, Route> _M_nameToRoute = new HashMap<>();


    //----------------------------------------SETTERS AND GETTERS--------------------------------//
    public int get_Color() { return _i_Color; }
    public void set_Color(int color) { _i_Color = color; }

    public int get_Weight() { return _i_Weight; }
    public void set_Weight(int weight) { _i_Weight = weight; }

    public String get_Owner() { return _S_Owner; }
    public void set_Owner(String owner) { _S_Owner = owner; }

    public Pair<String, String> get_ConnectingCities() { return _P_ConnectingCities; }
    public void set_ConnectingCities(Pair<String, String> pair) { _P_ConnectingCities = pair; }

    public PointF getPoint1() { return point1; }
    public void setPoint1(PointF point1) { this.point1 = point1; }

    public PointF getPoint2() { return point2; }
    public void setPoint2(PointF point2) {this.point2 = point2; }

    //---------------------------------------STATIC FUCNTIONS-------------------------------------//
    public static List<Route> get_allRoutes(){
        return new ArrayList<Route>(_M_nameToRoute.values()); }

    public static Map<String, Route> get_RoutesMap() { return _M_nameToRoute; }
    public static void set_RoutesMap(Map<String, Route> nameToRoute) { _M_nameToRoute = nameToRoute; }

    public static Boolean are_RoutesSet(){
        return _B_areRoutesSet;
    }

    public static void initAllRoutes() {
//        //-------------------------RED ROUTES---------------------//
        int color = Color.RED;
        int weight = 5;
        Pair<String, String> p = new Pair<>("Helena", "Omaha");
        String mappingName = "Helena-Omaha";
        PointF p1 = new PointF(823, 505);
        PointF p2 = new PointF(1118, 622);

        Route r = new Route(color, weight, null, p, p1, p2);
        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------SaltLakeCity-Denver------------------//
//        weight = 3;
//        p = new Pair<>("Salt Lake City", "Denver");
//        mappingName = "SaltLakeCity-Denver";
//        p1 = new PointF(678, 700);
//        p2 = new PointF(861, 746);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------Denver-OklahomaCity------------------//
//        weight = 4;
//        p = new Pair<>("Denver", "Oklahoma City");
//        mappingName = "Denver-OklahomaCity";
//        p1 = new PointF(901, 803);
//        p2 = new PointF(1136, 894);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------ElPaso-Dallas------------------//
//        weight = 4;
//        p = new Pair<>("El Paso", "Dallas");
//        mappingName = "ElPaso-Dallas";
//        p1 = new PointF(919, 1109);
//        p2 = new PointF(1171, 1072);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------Duluth-Chicago------------------//
//        weight = 3;
//        p = new Pair<>("Duluth", "Chicago");
//        mappingName = "Duluth-Chicago";
//        p1 = new PointF(1240, 486);
//        p2 = new PointF(1415, 554);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------NewOrleans-Miami------------------//
//        weight = 6;
//        p = new Pair<>("New Orleans", "Miami");
//        mappingName = "NewOrleans-Miami";
//        p1 = new PointF(1501, 1106);
//        p2 = new PointF(1840, 1187);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------NewYork-Boston------------------//
//        weight = 6;
//        p = new Pair<>("New York", "Boston");
//        mappingName = "NewYork-Boston";
//        p1 = new PointF(1972, 386);
//        p2 = new PointF(1874, 466);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------WHITE ROUTES------------------//
//        color = Color.WHITE;
//        weight = 6;
//        p = new Pair<>("Calgary", "Winnipeg");
//        mappingName = "Calgary-Winnipeg";
//        p1 = new PointF(617, 229);
//        p2 = new PointF(993, 241);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------SanFrancisco-SaltLakeCity------------------//
//        weight = 5;
//        p = new Pair<>("San Francisco", "Salt Lake City");
//        mappingName = "SanFrancisco-SaltLakeCity";
//        p1 = new PointF(321, 827);
//        p2 = new PointF(625, 732);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------Phoenix-Denver------------------//
//        weight = 5;
//        p = new Pair<>("Phoenix", "Denver");
//        mappingName = "Phoenix-Denver";
//        p1 = new PointF(651, 1019);
//        p2 = new PointF(861, 786);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------LittleRock-Nashville------------------//
//        weight = 3;
//        p = new Pair<>("Little Rock", "Nashville");
//        mappingName = "LittleRock-Nashville";
//        p1 = new PointF(1361, 902);
//        p2 = new PointF(1531, 831);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------SaintLouis-Chicago------------------//
//        weight = 2;
//        p = new Pair<>("Saint Louis", "Chicago");
//        mappingName = "SaintLouis-Chicago";
//        p1 = new PointF(1445, 616);
//        p2 = new PointF(1383, 722);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------Chicago-Toronto------------------//
//        weight = 4;
//        p = new Pair<>("Chicago", "Toronto");
//        mappingName = "Chicago-Toronto";
//        p1 = new PointF(1443, 552);
//        p2 = new PointF(1651, 412);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------Pittsburgh-NewYork------------------//
//        weight = 2;
//        p = new Pair<>("Pittsburgh", "New York");
//        mappingName = "Pittsburgh-NewYork";
//        p1 = new PointF(1712, 535);
//        p2 = new PointF(1816, 472);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------ORANGE ROUTES------------------//
//        color = 0xFF8C00;
//        weight = 5;
//        p = new Pair<>("San Francisco", "Salt Lake City");
//        mappingName = "SanFrancisco-SaltLakeCity";
//        p1 = new PointF(315, 808);
//        p2 = new PointF(618, 711);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------LasVegas-SaltLakeCity------------------//
//        weight = 3;
//        p = new Pair<>("Las Vegas", "Salt Lake City");
//        mappingName = "LasVegas-SaltLakeCity";
//        p1 = new PointF(538, 879);
//        p2 = new PointF(645, 738);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------Denver-KansasCity------------------//
//        weight = 4;
//        p = new Pair<>("Denver", "Kansas City");
//        mappingName = "Denver-KansasCity";
//        p1 = new PointF(927, 793);
//        p2 = new PointF(1178, 751);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------NewOrleans-Atlanta------------------//
//        weight = 4;
//        p = new Pair<>("New Orleans", "Atlanta");
//        mappingName = "NewOrleans-Atlanta";
//        p1 = new PointF(1477, 1102);
//        p2 = new PointF(1630, 900);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------Chicago-Pittsburgh------------------//
//        weight = 3;
//        p = new Pair<>("Chicago", "Pittsburgh");
//        mappingName = "Chicago-Pittsburgh";
//        p1 = new PointF(1466, 557);
//        p2 = new PointF(1659, 531);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------NewYork-Washington------------------//
//        weight = 2;
//        p = new Pair<>("New York", "Washington");
//        mappingName = "NewYork-Washington";
//        p1 = new PointF(1851, 497);
//        p2 = new PointF(1858, 620);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------GREEN ROUTES------------------//
//        color = Color.GREEN;
//        weight = 5;
//        p = new Pair<>("San Francisco", "Portland");
//        mappingName = "SanFrancisco-Portland";
//        p1 = new PointF(290, 483);
//        p2 = new PointF(264, 803);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------Helena-Denver------------------//
//        weight = 4;
//        p = new Pair<>("Helena", "Denver");
//        mappingName = "Helena-Denver";
//        p1 = new PointF(791, 500);
//        p2 = new PointF(882, 738);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------ElPaso-Houston------------------//
//        weight = 6;
//        p = new Pair<>("ElPaso", "Houston");
//        mappingName = "ElPaso-Houston";
//        p1 = new PointF(880, 1122);
//        p2 = new PointF(1259, 1153);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------LittleRock-NewOrleans------------------//
//        weight = 3;
//        p = new Pair<>("Little Rock", "New Orleans");
//        mappingName = "LittleRock-NewOrleans";
//        p1 = new PointF(1351, 924);
//        p2 = new PointF(1434, 1093);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------SaintLouis-Chicago------------------//
//        weight = 2;
//        p = new Pair<>("SaintLouis", "Chicago");
//        mappingName = "SaintLouis-Chicago";
//        p1 = new PointF(1426, 605);
//        p2 = new PointF(1364, 711);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------SaintLouis-Pittsburgh------------------//
//        weight = 5;
//        p = new Pair<>("SaintLouis", "Pittsburgh");
//        mappingName = "SaintLouis-Pittsburgh";
//        p1 = new PointF(1393, 736);
//        p2 = new PointF(1670, 579);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------Pittsburgh-NewYork------------------//
//        weight = 2;
//        p = new Pair<>("Pittsburgh", "New York");
//        mappingName = "Pittsburgh-NewYork";
//        p1 = new PointF(1723, 554);
//        p2 = new PointF(1827, 491);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------BLUE ROUTES------------------//
//        color = Color.BLUE;
//        weight = 6;
//        p = new Pair<>("Portland", "Salt Lake City");
//        mappingName = "Portland-SaltLakeCity";
//        p1 = new PointF(330, 468);
//        p2 = new PointF(635, 681);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------Helena-Winnipeg------------------//
//        weight = 4;
//        p = new Pair<>("Helena", "Winnipeg");
//        mappingName = "Helena-Winnipeg";
//        p1 = new PointF(983, 275);
//        p2 = new PointF(803, 457);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------SantaFe-OklahomaCity------------------//
//        weight = 3;
//        p = new Pair<>("Santa Fe", "Oklahoma City");
//        mappingName = "SantaFe-OklahomaCity";
//        p1 = new PointF(910, 934);
//        p2 = new PointF(1096, 916);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------Omaha-Chicago------------------//
//        weight = 4;
//        p = new Pair<>("Omaha", "Chicago");
//        mappingName = "Omaha-Chicago";
//        p1 = new PointF(1190, 640);
//        p2 = new PointF(1420, 582);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------KansasCity-SaintLouis------------------//
//        weight = 2;
//        p = new Pair<>("Kansas City", "Saint Louis");
//        mappingName = "KansasCity-SaintLouis";
//        p1 = new PointF(1226, 721);
//        p2 = new PointF(1348, 718);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------Atlanta-Miami------------------//
//        weight = 5;
//        p = new Pair<>("Atlanta", "Miami");
//        mappingName = "Atlanta-Miami";
//        p1 = new PointF(1653, 913);
//        p2 = new PointF(1850, 1164);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------Montreal-NewYork------------------//
//        weight = 3;
//        p = new Pair<>("Montreal", "New York");
//        mappingName = "Montreal-NewYork";
//        p1 = new PointF(1813, 262);
//        p2 = new PointF(1843, 446);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);


        //-------------------PURPLE(BLACK) ROUTES------------------//
        color = 0x733e98;
        weight = 6;
        p = new Pair<>("Los Angeles", "El Paso");
        mappingName = "LosAngeles-ElPaso";
        p1 = new PointF(446, 1044);
        p2 = new PointF(821, 1114);

        r = new Route(color, weight, null, p, p1, p2);
        _M_nameToRoute.put(mappingName, r);

        //-------------------Helena-Duluth------------------//
        weight = 6;
        p = new Pair<>("Helena", "Duluth");
        mappingName = "Helena-Duluth";
        p1 = new PointF(808, 477);
        p2 = new PointF(1192, 477);

        r = new Route(color, weight, null, p, p1, p2);
        _M_nameToRoute.put(mappingName, r);

        //-------------------Winnipeg-Duluth------------------//
        weight = 4;
        p = new Pair<>("Winnipeg", "Duluth");
        mappingName = "Winnipeg-Duluth";
        p1 = new PointF(1027, 272);
        p2 = new PointF(1207, 454);

        r = new Route(color, weight, null, p, p1, p2);
        _M_nameToRoute.put(mappingName, r);


        //-------------------SaultSt.Marie-Montreal------------------//
        weight = 5;
        p = new Pair<>("Sault St. Marie", "Montreal");
        mappingName = "SaultSt.Marie-Montreal";
        p1 = new PointF(1779, 220);
        p2 = new PointF(1477, 329);

        r = new Route(color, weight, null, p, p1, p2);
        _M_nameToRoute.put(mappingName, r);


        //--------------------Chicago-Pittsburgh------------------//
        weight = 3;
        p = new Pair<>("Chicago", "Pittsburgh");
        mappingName = "Chicago-Pittsburgh";
        p1 = new PointF(1470, 579);
        p2 = new PointF(1660, 555);

        r = new Route(color, weight, null, p, p1, p2);
        _M_nameToRoute.put(mappingName, r);


        //-------------------Nashville-Raleigh------------------//
        weight = 3;
        p = new Pair<>("Nashville", "Raleigh");
        mappingName = "Nashville-Raleigh";
        p1 = new PointF(1559, 789);
        p2 = new PointF(1739, 746);

        r = new Route(color, weight, null, p, p1, p2);
        _M_nameToRoute.put(mappingName, r);


        //-------------------NewYork-Washington------------------//
        weight = 2;
        p = new Pair<>("New York", "Washington");
        mappingName = new String("NewYork-Washington");
        p1 = new PointF(1873, 496);
        p2 = new PointF(1880, 619);

        r = new Route(color, weight, null, p, p1, p2);
        _M_nameToRoute.put(mappingName, r);

//        //-------------------YELLOW ROUTES------------------//
//        color = Color.YELLOW;
//        weight = 3;
//        p = new Pair<>("San Francisco", "Los Angeles");
//        mappingName = "SanFrancisco-LosAngeles";
//        p1 = new PointF(276, 857);
//        p2 = new PointF(389, 1016);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------Seattle-Helena------------------//
//        weight = 6;
//        p = new Pair<>("Seattle", "Helena");
//        mappingName = "Seattle-Helena";
//        p1 = new PointF(372, 390);
//        p2 = new PointF(740, 474);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------SaltLakeCity-Denver------------------//
//        weight = 3;
//        p = new Pair<>("Salt Lake City", "Denver");
//        mappingName = "SaltLakeCity-Denver";
//        p1 = new PointF(673, 721);
//        p2 = new PointF(856, 765);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-------------------ElPaso-OklahomaCity------------------//
//        weight = 5;
//        p = new Pair<>("El Paso", "Oklahoma City");
//        mappingName = "ElPaso-OklahomaCity";
//        p1 = new PointF(888, 1093);
//        p2 = new PointF(1150, 915);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------NewOrleans-Atlanta------------------//
//        weight = 4;
//        p = new Pair<>("New Orleans", "Atlanta");
//        mappingName = "NewOrleans-Atlanta";
//        p1 = new PointF(1460, 1086);
//        p2 = new PointF(1614, 882);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------Nashville-Pittsburgh------------------//
//        weight = 4;
//        p = new Pair<>("Nashville", "Pittsburgh");
//        mappingName = "Nashville-Pittsburgh";
//        p1 = new PointF(1535, 793);
//        p2 = new PointF(1685, 596);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------NewYork-Boston------------------//
//        weight = 2;
//        p = new Pair<>("New York", "Boston");
//        mappingName = "NewYork-Boston";
//        p1 = new PointF(1957, 369);
//        p2 = new PointF(1867, 453);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-------------------GRAY ROUTES------------------//
//
//        //-----------------Portland-Seattle-1---------------//
//        color = Color.GRAY;
//        weight = 1;
//        p = new Pair<>("Portland", "Seattle");
//        mappingName = "Portland-Seattle-1";
//        p1 = new PointF(324, 387);
//        p2 = new PointF(305, 434);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Portland-Seattle-2---------------//
//        weight = 1;
//        p = new Pair<>("Portland", "Seattle");
//        mappingName = "Portland-Seattle-2";
//        p1 = new PointF(344, 395);
//        p2 = new PointF(327, 441);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------Seattle-Vancouver-1---------------//
//        weight = 1;
//        p = new Pair<>("Seattle", "Vancouver");
//        mappingName = "Seattle-Vancouver-1";
//        p1 = new PointF(335, 299);
//        p2 = new PointF(335, 340);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Seattle-Vancouver-2---------------//
//        weight = 1;
//        p = new Pair<>("Seattle", "Vancouver");
//        mappingName = "Seattle-Vancouver-2";
//        p1 = new PointF(357, 299);
//        p2 = new PointF(357, 340);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------Vancouver-Calgary----------------//
//        weight = 3;
//        p = new Pair<>("Vancouver", "Calgary");
//        mappingName = "Vancouver-Calgary";
//        p1 = new PointF(385, 257);
//        p2 = new PointF(555, 238);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------Seattle-Calgary----------------//
//        weight = 4;
//        p = new Pair<>("Seattle", "Calgary");
//        mappingName = "Seattle-Calgary";
//        p1 = new PointF(376, 363);
//        p2 = new PointF(582, 260);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------Calgary-Helena----------------//
//        weight = 4;
//        p = new Pair<>("Calgary", "Helena");
//        mappingName = "Calgary-Helena";
//        p1 = new PointF(770, 459);
//        p2 = new PointF(609, 258);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------LosAngeles-LasVegas----------------//
//        weight = 2;
//        p = new Pair<>("Los Angeles", "Las Vegas");
//        mappingName = "LosAngeles-LasVegas";
//        p1 = new PointF(431, 990);
//        p2 = new PointF(505, 896);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------LosAngeles-Phoenix----------------//
//        weight = 3;
//        p = new Pair<>("Los Angeles", "Phoenix");
//        mappingName = "LosAngeles-Phoenix";
//        p1 = new PointF(441, 1013);
//        p2 = new PointF(628, 1020);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------Phoenix-ElPaso----------------//
//        weight = 3;
//        p = new Pair<>("Phoenix", "El Paso");
//        mappingName = "Phoenix-ElPaso";
//        p1 = new PointF(662, 1040);
//        p2 = new PointF(840, 1098);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Phoenix-SantaFe----------------//
//        weight = 3;
//        p = new Pair<>("Phoenix", "Santa Fe");
//        mappingName = "Phoenix-SantaFe";
//        p1 = new PointF(681, 1021);
//        p2 = new PointF(854, 952);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------ElPaso-SantaFe----------------//
//        weight = 2;
//        p = new Pair<>("El Paso", "Santa Fe");
//        mappingName = "ElPaso-SantaFe";
//        p1 = new PointF(876, 960);
//        p2 = new PointF(868, 1081);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------SantaFe-Denver----------------//
//        weight = 2;
//        p = new Pair<>("Santa Fe", "Denver");
//        mappingName = "SantaFe-Denver";
//        p1 = new PointF(884, 794);
//        p2 = new PointF(876, 916);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------Winnipeg-SaultSt.Marie----------------//
//        weight = 6;
//        p = new Pair<>("Winnipeg", "Sault St. Marie");
//        mappingName = "Winnipeg-SaultSt.Marie";
//        p1 = new PointF(1054, 255);
//        p2 = new PointF(1430, 333);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Duluth-SaultSt.Marie----------------//
//        weight = 3;
//        p = new Pair<>("Duluth", "Sault St. Marie");
//        mappingName = "Duluth-SaultSt.Marie";
//        p1 = new PointF(1260, 428);
//        p2 = new PointF(1434, 359);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------SaultSt.Marie-Toronto----------------//
//        weight = 2;
//        p = new Pair<>("Sault St. Marie", "Toronto");
//        mappingName = "SaultSt.Marie-Toronto";
//        p1 = new PointF(1617, 373);
//        p2 = new PointF(1497, 350);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------Omaha-Duluth-1----------------//
//        weight = 2;
//        p = new Pair<>("Omaha", "Duluth");
//        mappingName = "Omaha-Duluth-1";
//        p1 = new PointF(1166, 615);
//        p2 = new PointF(1200, 498);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Omaha-Duluth-2----------------//
//        weight = 2;
//        p = new Pair<>("Omaha", "Duluth");
//        mappingName = "Omaha-Duluth-2";
//        p1 = new PointF(1187, 620);
//        p2 = new PointF(1221, 504);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Omaha-KansasCity-1----------------//
//        weight = 2;
//        p = new Pair<>("Omaha", "Kansas City");
//        mappingName = "Omaha-KansasCity-1";
//        p1 = new PointF(1190, 714);
//        p2 = new PointF(1168, 662);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Omaha-KansasCity-2----------------//
//        weight = 2;
//        p = new Pair<>("Omaha", "Kansas City");
//        mappingName = "Omaha-KansasCity-2";
//        p1 = new PointF(1210, 706);
//        p2 = new PointF(1188, 653);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------Oklahoma-KansasCity-1----------------//
//        weight = 2;
//        p = new Pair<>("Oklahoma", "Kansas City");
//        mappingName = "Oklahoma-KansasCity-1";
//        p1 = new PointF(1164, 877);
//        p2 = new PointF(1195, 761);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Oklahoma-KansasCity-2----------------//
//        weight = 2;
//        p = new Pair<>("Oklahoma", "Kansas City");
//        mappingName = "Oklahoma-KansasCity-2";
//        p1 = new PointF(1186, 884);
//        p2 = new PointF(1215, 767);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Oklahoma-Dallas-1----------------//
//        weight = 2;
//        p = new Pair<>("Oklahoma", "Dallas");
//        mappingName = "Oklahoma-Dallas-1";
//        p1 = new PointF(1192, 1044);
//        p2 = new PointF(1172, 924);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Oklahoma-Dallas-2----------------//
//        weight = 2;
//        p = new Pair<>("Oklahoma", "Kansas City");
//        mappingName = "Oklahoma-Dallas-2";
//        p1 = new PointF(1214, 1040);
//        p2 = new PointF(1194, 920);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------Oklahoma-LittleRock----------------//
//        weight = 2;
//        p = new Pair<>("Oklahoma", "Little Rock");
//        mappingName = "Oklahoma-LittleRock";
//        p1 = new PointF(1195, 903);
//        p2 = new PointF(1317, 899);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------Dallas-Houston-1---------------//
//        weight = 1;
//        p = new Pair<>("Dallas", "Houston");
//        mappingName = "Dallas-Houston-1";
//        p1 = new PointF(1252, 1127);
//        p2 = new PointF(1218, 1082);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Dallas-Houston-2---------------//
//        weight = 1;
//        p = new Pair<>("Dallas", "Houston");
//        mappingName = "Dallas-Houston-2";
//        p1 = new PointF(1270, 1114);
//        p2 = new PointF(1236, 1069);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------Dallas-LittleRock---------------//
//        weight = 2;
//        p = new Pair<>("Dallas", "Little Rock");
//        mappingName = "Dallas-LittleRock";
//        p1 = new PointF(1241, 1024);
//        p2 = new PointF(1312, 926);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------Houston-NewOrleans---------------//
//        weight = 2;
//        p = new Pair<>("Houston", "New Orleans");
//        mappingName = "Houston-NewOrleans";
//        p1 = new PointF(1307, 1135);
//        p2 = new PointF(1428, 1115);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------LittleRock-SaintLouis---------------//
//        weight = 2;
//        p = new Pair<>("Little Rock", "Saint Louis");
//        mappingName = "LittleRock-SaintLouis";
//        p1 = new PointF(1365, 764);
//        p2 = new PointF(1346, 885);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------SaintLouis-Nashville---------------//
//        weight = 2;
//        p = new Pair<>("Saint Louis", "Nashville");
//        mappingName = "SaintLouis-Nashville";
//        p1 = new PointF(1388, 772);
//        p2 = new PointF(1505, 806);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------Nashville-Atlanta---------------//
//        weight = 1;
//        p = new Pair<>("Nashville", "Atlanta");
//        mappingName = "Nashville-Atlanta";
//        p1 = new PointF(1609, 862);
//        p2 = new PointF(1561, 830);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------Atlanta-Charleston---------------//
//        weight = 2;
//        p = new Pair<>("Atlanta", "Charleston");
//        mappingName = "Atlanta-Charleston";
//        p1 = new PointF(1661, 889);
//        p2 = new PointF(1783, 896);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------Atlanta-Raleigh-1--------------//
//        weight = 2;
//        p = new Pair<>("Atlanta", "Raleigh");
//        mappingName = "Atlanta-Raleigh-1";
//        p1 = new PointF(1642, 858);
//        p2 = new PointF(1734, 775);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Atlanta-Raleigh-2--------------//
//        weight = 2;
//        p = new Pair<>("Atlanta", "Raleigh");
//        mappingName = "Atlanta-Raleigh-2";
//        p1 = new PointF(1657, 873);
//        p2 = new PointF(1748, 793);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Raleigh-Charleston--------------//
//        weight = 2;
//        p = new Pair<>("Raleigh", "Charleston");
//        mappingName = "Raleigh-Charleston";
//        p1 = new PointF(1774, 782);
//        p2 = new PointF(1822, 864);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------Raleigh-Washington-1--------------//
//        weight = 2;
//        p = new Pair<>("Raleigh", "Washington");
//        mappingName = "Raleigh-Washington-1";
//        p1 = new PointF(1850, 655);
//        p2 = new PointF(1768, 747);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Raleigh-Washington-2--------------//
//        weight = 2;
//        p = new Pair<>("Raleigh", "Washington");
//        mappingName = "Raleigh-Washington-2";
//        p1 = new PointF(1867, 670);
//        p2 = new PointF(1784, 762);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//
//        //-----------------Pittsburgh-Raleigh--------------//
//        weight = 2;
//        p = new Pair<>("Pittsburgh", "Raleigh");
//        mappingName = "Pittsburgh-Raleigh";
//        p1 = new PointF(1706, 607);
//        p2 = new PointF(1743, 722);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Pittsburgh-Washington--------------//
//        weight = 2;
//        p = new Pair<>("Pittsburgh", "Washington");
//        mappingName = "Pittsburgh-Washington";
//        p1 = new PointF(1730, 577);
//        p2 = new PointF(1836, 635);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Toronto-Pittsburgh--------------//
//        weight = 2;
//        p = new Pair<>("Toronto", "Pittsburgh");
//        mappingName = "Toronto-Pittsburgh";
//        p1 = new PointF(1685, 532);
//        p2 = new PointF(1671, 410);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Toronto-Montreal-------------//
//        weight = 3;
//        p = new Pair<>("Toronto", "Montreal");
//        mappingName = "Toronto-Montreal";
//        p1 = new PointF(1664, 365);
//        p2 = new PointF(1794, 237);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Montreal-Boston-1-------------//
//        weight = 2;
//        p = new Pair<>("Montreal", "Boston");
//        mappingName = "Montreal-Boston-1";
//        p1 = new PointF(1848, 266);
//        p2 = new PointF(1946, 342);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);
//
//        //-----------------Montreal-Boston-2-------------//
//        weight = 2;
//        p = new Pair<>("Montreal", "Boston");
//        mappingName = "Montreal-Boston-2";
//        p1 = new PointF(1862, 250);
//        p2 = new PointF(1958, 325);
//
//        r = new Route(color, weight, null, p, p1, p2);
//        _M_nameToRoute.put(mappingName, r);

    }
}
