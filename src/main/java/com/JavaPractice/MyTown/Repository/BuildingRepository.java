package com.JavaPractice.MyTown.Repository;

import com.JavaPractice.MyTown.model.Building;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BuildingRepository implements IBuildingRepository {
    private static final String FILE_NAME="C:\\Users\\miste\\Desktop\\ОП\\last chance\\MyTown\\src\\main\\resources\\BuildingContext.xml";

    public Building saveBuilding(Building building){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e){
            throw new RuntimeException(e);
    }
        File file = new File(FILE_NAME);
        Document document;
        Element root;
        if (file.exists()){
            try {
                document = documentBuilder.parse(file);
                root = document.getDocumentElement();
            } catch (SAXException | IOException e){
                throw new RuntimeException(e);
            }
        } else {
            document = documentBuilder.newDocument();
            root = document.createElement("buildings");
            document.appendChild(root);
        }

        Element element = document.createElement("building");
        root.appendChild(element);

        Long idField = building.getId();
        Element idElement = document.createElement("id");
        idElement.appendChild(document.createTextNode(String.valueOf(idField)));
        element.appendChild(idElement);

        String builtField = building.getBuilt();
        Element builtElement = document.createElement("built");
        builtElement.appendChild(document.createTextNode(String.valueOf(builtField)));
        element.appendChild(builtElement);

        int creationDateField = building.getCreationDate();
        Element creationDateElement = document.createElement("creationDate");
        creationDateElement.appendChild(document.createTextNode(String.valueOf(creationDateField)));
        element.appendChild(creationDateElement);

        String titleField = building.getTitle();
        Element titleElement = document.createElement("title");
        titleElement.appendChild(document.createTextNode(String.valueOf(titleField)));
        element.appendChild(titleElement);

        String ownerField = building.getOwner();
        Element ownerElement = document.createElement("owner");
        ownerElement.appendChild(document.createTextNode(String.valueOf(ownerField)));
        element.appendChild(ownerElement);

        String typeBuildingField = building.getTypeBuilding();
        Element typeBuildingElement = document.createElement("typeBuilding");
        typeBuildingElement.appendChild(document.createTextNode(String.valueOf(typeBuildingField)));
        element.appendChild(typeBuildingElement);

        String addressField = building.getAddress();
        Element addressElement = document.createElement("address");
        addressElement.appendChild(document.createTextNode(String.valueOf(addressField)));
        element.appendChild(addressElement);

        int floorsField = building.getFloors();
        Element floorsElement = document.createElement("floors");
        floorsElement.appendChild(document.createTextNode(String.valueOf(floorsField)));
        element.appendChild(floorsElement);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        DOMSource source = new DOMSource(document);

        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StreamResult result = new StreamResult(outputStream);

        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return building;
    }

    public void deleteBuilding(Long id){
        if (!new File(FILE_NAME).exists()){
            throw new RuntimeException("File not found: " + FILE_NAME);
        }

        FileInputStream fileInputStream;
        try{
            fileInputStream = new FileInputStream(FILE_NAME);
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try{
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document;
        try {
            document = builder.parse(fileInputStream);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        NodeList nodeList = document.getElementsByTagName("building");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            Long idField = Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent());

            if (idField == id) {
                node.getParentNode().removeChild(node);
                break;
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        DOMSource source = new DOMSource(document);

        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StreamResult result = new StreamResult(outputStream);

        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Building> getAllBuildings() {

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document;
        try {
            document = builder.parse(fileInputStream);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        NodeList nodeList = document.getElementsByTagName("buildings");
        List<Building> buildings = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node buildingsNode = nodeList.item(i);
            NodeList buildingNodes = buildingsNode.getChildNodes();

            for (int j = 0; j < buildingNodes.getLength(); j++) {
                Node buildingNode = buildingNodes.item(j);

                if (buildingNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) buildingNode;

                    Long idField = Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent());
                    String builtField = element.getElementsByTagName("built").item(0).getTextContent();
                    int creationDateField = Integer.parseInt(element.getElementsByTagName("creationDate").item(0).getTextContent());
                    String titleField = element.getElementsByTagName("title").item(0).getTextContent();
                    String ownerField = element.getElementsByTagName("owner").item(0).getTextContent();
                    String typeBuildingField = element.getElementsByTagName("typeBuilding").item(0).getTextContent();
                    String addressField = element.getElementsByTagName("address").item(0).getTextContent();
                    int floorsField = Integer.parseInt(element.getElementsByTagName("floors").item(0).getTextContent());

                    Building building = new Building(idField,builtField,creationDateField,titleField,ownerField,typeBuildingField,addressField,floorsField);
                    buildings.add(building);
                }
            }
        }
        return buildings;
    }

    public Building getBuildingById(Long id) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document;
        try {
            document = builder.parse(fileInputStream);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        NodeList nodeList = document.getElementsByTagName("building");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            Long idField = Long.parseLong((element.getElementsByTagName("id").item(0).getTextContent()));

            if (idField == id) {
                String builtField = element.getElementsByTagName("built").item(0).getTextContent();
                int creationDateField = Integer.parseInt(element.getElementsByTagName("creationDate").item(0).getTextContent());
                String titleField = element.getElementsByTagName("title").item(0).getTextContent();
                String ownerField = element.getElementsByTagName("owner").item(0).getTextContent();
                String typeBuildingField = element.getElementsByTagName("typeBuilding").item(0).getTextContent();
                String addressField = element.getElementsByTagName("address").item(0).getTextContent();
                int floorsField = Integer.parseInt(element.getElementsByTagName("floors").item(0).getTextContent());
                return new Building(idField,builtField,creationDateField,titleField,ownerField,typeBuildingField,addressField,floorsField);
            }
        }
        return null;
    }
    public Building updateBuilding(Building building) {
        Long id = building.getId();

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document;
        try {
            document = builder.parse(fileInputStream);
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        NodeList nodeList = document.getElementsByTagName("building");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            Long idField = Long.parseLong((element.getElementsByTagName("id").item(0).getTextContent()));

            if (idField.equals(id)) {
                String builtField = element.getElementsByTagName("built").item(0).getTextContent();
                int creationDateField = Integer.parseInt(element.getElementsByTagName("creationDate").item(0).getTextContent());
                String titleField = element.getElementsByTagName("title").item(0).getTextContent();
                String ownerField = element.getElementsByTagName("owner").item(0).getTextContent();
                String typeBuildingField = element.getElementsByTagName("typeBuilding").item(0).getTextContent();
                String addressField = element.getElementsByTagName("address").item(0).getTextContent();
                int floorsField = Integer.parseInt(element.getElementsByTagName("floors").item(0).getTextContent());

                try {
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(document);
                    StreamResult result = new StreamResult(new File(FILE_NAME));
                    transformer.transform(source, result);
                } catch (TransformerException e) {
                    throw new RuntimeException(e);
                }

                return building;
            }
        }
        return null;
    }
}
