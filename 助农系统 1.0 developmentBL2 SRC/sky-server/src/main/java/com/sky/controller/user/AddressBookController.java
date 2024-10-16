package com.sky.controller.user;



import com.sky.dto.AddressBookPostDto;
import com.sky.dto.AddressBookPutDto;
import com.sky.result.Result;
import com.sky.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/addressBook")
public class AddressBookController {
    @Autowired
    private AddressService addressService;

    @PostMapping
    Result addAddressBook(@RequestBody AddressBookPostDto addressBookPostDto){
//        System.out.println("addressBookPostDto = " + addressBookPostDto);
//        //return Result.success();
//        return null;
        Result result = addressService.addAddress(addressBookPostDto);
        return result;
    }
    @DeleteMapping("/{id}")
    Result deleteAddressBook(@PathVariable Integer id){
//        System.out.println("id = " + id);
//        return null;
        Result result = addressService.deleteById(id);
        return result;
    }
    @PutMapping("/{id}")
    Result updateAddressBook(@PathVariable Integer id, @RequestBody AddressBookPutDto addressBookPutDto){
        Result result = addressService.updateAddressBook(id,addressBookPutDto);
        return result;

    }
    @GetMapping("/{id}")
    Result getAddressBook(@PathVariable Integer id){
        Result result=addressService.queryById(id);

        return result;
    }
    @GetMapping
    Result getAllAddressBook(){
//        System.out.println("addressService = " + addressService);
//        return null;
        Result result=addressService.queryAll();
        return result;
    }


}
