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

  `{  
      "error": false,  
      "name": "Winnerawan T",  
      "email": "admin@winnerawan.net",  
      "apiKey": "17a3d9bab2b1ddc1cef9a77e7b8de3a3",  
      "createdAt": "2016-10-23 16:12:24"
    }`
