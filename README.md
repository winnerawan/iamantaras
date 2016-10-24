# iamantaras-api
#Ikatan Alumni Man Tambak Beras

iamantaras REST API
## Installation

### Requqirements
* update later

# API Document
## Project Info
Test Address: http://xxx.xxx.xxx.xxx/ (update later)
## API
### User

-------------METHODS WITHOUT AUTHENTICATION---------------

* [`Register`](https://github.com/winnerawan/iamantaras/users.md#register)

**`POST` `/api/v1/register`**
**_Parameters_**
* `name`
* `email`
* `password`
* `jenis_kelamin`
* `angakatan_lulus`
* `jururusan_id`
* `asrama_id`


* [`Login`](https://github.com/winnerawan/iamantaras/users.md#login)

**`POST` `/api/v1/login`**
**_Parameters_**
* `email`
* `password`

Response
```
    {  
      "error": false,  
      "name": "Winnerawan T",  
      "email": "admin@winnerawan.net",  
      "apiKey": "17a3d9bab2b1ddc1cef9a77e7b8de3a3",  
      "createdAt": "2016-10-23 16:12:24"
    }
```
-------------METHODS WITH AUTHENTICATION---------------
`Karena API ini dibuat hanya untuk anggota alumni, maka hak untuk mengakses datanya dibatasi. Pada REST API ini digenerate secara random dan harus disimpan untuk digunakan pada header Authorization. `

* [`Information User`](https://github.com/winnerawan/iamantaras/users.md#information)
**`GET` `/api/v1/myInformation`**
**_Header_**
* `api_key`

Response request tanpa header authorization

```
    {  
    "error": true,  
    "message": "Api key is misssing"
    }
```

Response request dengan header authorization
```
    {  
      "error": false,  
      "users": [    
        {      
        "id": 1,      
        "name": "Winnerawan T",      
        "foto": "http://76.164.195.171/api/v1/default-foto.png",     
        "angkatan": 2016,      
        "jurusan": "IPA 1",      
        "bio": "test",      
        "profesi": "xdgfhg",      
        "keahlian": null,      
        "penghargaan": null,      
        "minat_profesi": null,      
        "referensi_rekomendasi": null,      
        "telp": null,      
        "jenis_kelamin": "Laki"     
        }  
     ]
   }
```
