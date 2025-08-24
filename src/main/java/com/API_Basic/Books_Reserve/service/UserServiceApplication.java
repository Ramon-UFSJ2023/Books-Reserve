package com.API_Basic.Books_Reserve.service;
import com.API_Basic.Books_Reserve.model.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class UserServiceApplication {
    private List<UserData> usersList = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String UserJsonPATH = "classpath:SavesJSON/usersSaves.json";

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void loadUsers(){
        try {
            //Passando o caminho pra uma instancia de um objeto Resource que vai armazenar os usuarios guardados do .json
            Resource resource = resourceLoader.getResource(UserJsonPATH);
            //aq ele suga os dados do arquivo resource
            InputStream inputStream = resource.getInputStream();
            //verifica se o tamanho é maior que zero bytes caso sim transformada os dados em objeto java usando objectMapper
            if(inputStream.available()>0){
                //o TypeReference é para o compilador não converter os dados do .json em string
                this.usersList = objectMapper.readValue(inputStream,new TypeReference <List<UserData>>() {});
            }
        }catch (IOException e){
            System.err.println("Erro ao carregar arquivo de usuarios: "+ e.getMessage());
        }
    }

    private void saveUsers(){
        try {
            Resource resource = resourceLoader.getResource(UserJsonPATH);
            File file = resource.getFile();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, this.usersList);
        }catch (IOException e){
            System.err.println("Erro ao salvar usuario no arquivo: "+ e.getMessage());
        }
    }

    public UserData createUser(UserData user){
        user.setID(usersList.stream().mapToInt(UserData ::getID).max().orElse(0)+1);
        usersList.add(user);
        saveUsers();
        return user;
    }
    public List<UserData> getAllUsers(){
        return this.usersList;
    }
    public Optional<UserData> getUserByID(int id){
        return usersList.stream().filter(searchByID -> searchByID.getID() == id).findFirst();
    }
    public Optional<UserData> uptadeUserByID(int id, UserData newUser){
        return getUserByID(id).map(user ->{
            newUser.setID(id);
            BeanUtils.copyProperties(newUser, user);
            saveUsers();
            return user;
        });
    }
    public void deleteUserByID(int id){
        getUserByID(id).ifPresent(user -> usersList.remove(user));
        saveUsers();
    }


}
