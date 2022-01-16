const app = Vue.createApp({
    data(){
        return{
            listClient: [],
            selected: "",
            lengthText: 0,

            /* CLIENT */
            listClients: [],
            client:{
                code: "", firstname: "", lastname: "", username: "", email: "", password: "", profile:"", role: ""
            },
            type: "password", hidden: true, show: false,
            filterClient: [],
            searchClient: "",
            noClient: false,

            /* SOAP */
            listSoaps: [],
            soap:{
                code: "", name: "", description: "", url: "", price: "", stock: ""
            },
            filterSoap: [],
            searchSoap: "",
            noSoap: false,

            /* SHAMPOO */
            listShampoos: [],
            shampoo:{
                code: "", name: "", description: "", url: "", price: "", stock: ""
            },
            filterShampoo: [],
            searchShampoo: "",
            noShampoo: false,

            /* CONDITIONER */
            listConditioners: [],
            conditioner:{
                code: "", name: "", description: "", url: "", price: "", stock: ""
            },
            filterConditioner: [],
            searchConditioner: "",
            noConditioner: false
        }
    },
    created(){
        this.loadClientCurrent(),
        this.loadClients(),
        this.loadClientID(),
        this.loadSoaps(),
        this.loadSoapID(),
        this.loadShampoos(),
        this.loadShampooID(),
        this.loadConditioners(),
        this.loadConditionerID()
    },
    methods:{
        loadClientCurrent(){
            axios.get("/api/clients/current")
            .then(response => {
                this.listClient = response.data
            })
        },
        signOut(){
            swal({
                text: "¿Estás seguro que quiere cerrar su sesión?",
                icon:"warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.post("/api/logout")
                    .then(response=>{
                        window.location.replace("../webPages/index.html")
                    })
                }
            })
        },

        /* DATA CLIENT */

        loadClients(){
            axios.get("/api/admin/clients")
            .then(response => {
                this.listClients = response.data

                this.orderByID(this.listClients)
            })
        },
        loadClientID(){
            const urlParams = new URLSearchParams(window.location.search);
            this.clientID = urlParams.get("id");

            axios.get(`/api/admin/clients/${this.clientID}`)
            .then(response => {
                this.client.id = response.data.id
                this.client.code = response.data.code
                this.client.firstname = response.data.firstname
                this.client.lastname = response.data.lastname
                this.client.username = response.data.username
                this.client.email = response.data.email
                this.client.profile = response.data.profile
                this.client.role = response.data.role
            })
        },
        addClient(){
            swal({
                title: "Confirmación",
                text: "¿Está seguro de querer registrar este producto?",
                icon: "warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.post("/api/clients",{
                        firstname: this.client.firstname,
                        lastname: this.client.lastname,
                        username: this.client.username,
                        email: this.client.email,
                        password: this.client.password
                    })
                    .then(response => {
                        swal({
                            title: "¡Registro exitoso!",
                            icon: "success",
                            button: true
                        })
                        .then(confirmation => {
                            window.location.reload()
                        })
                    })
                    .catch(error => {
                        swal({
                            text: error.response.data,
                            icon: "error"
                        })
                    })
                }
            })
        },
        changeDataClient(){
            const urlParams = new URLSearchParams(window.location.search);
            this.clientID = urlParams.get("id");

            swal({
                title: "Confirmación",
                text: "¿Está seguro de querer guardar los nuevos datos?",
                icon: "warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.put(`/api/admin/clients/${this.clientID}`,{
                        firstname: this.client.firstname,
                        lastname: this.client.lastname,
                        username: this.client.username,
                        email: this.client.email,
                        role: this.client.role
                    })
                    .then(response => {
                        swal({
                            title: "Datos guardados",
                            icon: "success",
                            button: true
                        })
                        .then(response =>{
                            window.location.reload()
                        })
                    })
                    .catch(error => {
                        swal({
                            title: "¡Atención!",
                            text: error.response.data,
                            icon: "error"
                        })
                    })
                }
            })
        },
        deleteClient(){
            const urlParams = new URLSearchParams(window.location.search);
            this.clientID = urlParams.get("id");

            swal({
                title: "Confirmación",
                text: "¿Está seguro de querer eliminar este usuario?",
                icon: "warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.delete(`/api/admin/clients/${this.clientID}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                    .then(response => {
                        swal({
                            title: "Usuario eliminado",
                            icon: "success",
                            button: true
                        })
                        .then(response =>{
                            window.location.replace("manager-clients.html")
                        })
                    })
                }
            })
        },

        /* DATA SOAP */

        loadSoaps(){
            axios.get("/api/admin/soaps")
            .then(response => {
                this.listSoaps = response.data

                this.orderByID(this.listSoaps)
            })
        },
        loadSoapID(){
            const urlParams = new URLSearchParams(window.location.search);
            this.soapID = urlParams.get("id");

            axios.get(`/api/admin/soaps/${this.soapID}`)
            .then(response => {
                this.soap.code = response.data.code
                this.soap.name = response.data.name
                this.soap.description = response.data.description
                this.soap.url = response.data.url
                this.soap.price = response.data.price
                this.soap.stock = response.data.stock
            })
        },
        addSoap(){
            swal({
                title: "Confirmación",
                text: "¿Está seguro de querer registrar este producto?",
                icon: "warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.post("/api/admin/soaps",{
                        name: this.soap.name,
                        description: this.soap.description,
                        url: this.soap.url,
                        price: this.soap.price,
                        stock: this.soap.stock
                    })
                    .then(response => {
                        swal({
                            title: "Datos guardados",
                            icon: "success",
                            button: true
                        })
                        .then(response =>{
                            window.location.reload()
                        })
                    })
                    .catch(error => {
                        swal({
                            title: "¡Atención!",
                            text: error.response.data,
                            icon: "error"
                        })
                    })
                }
            })
        },
        changeDataSoap(){
            const urlParams = new URLSearchParams(window.location.search);
            this.soapID = urlParams.get("id");

            swal({
                title: "Confirmación",
                text: "¿Está seguro de querer guardar los nuevos datos?",
                icon: "warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.put(`/api/admin/soaps/${this.soapID}`,{
                        name: this.soap.name,
                        description: this.soap.description,
                        url: this.soap.url,
                        price: this.soap.price,
                        stock: this.soap.stock
                    })
                    .then(response => {
                        swal({
                            title: "Datos guardados",
                            icon: "success",
                            button: true
                        })
                        .then(response =>{
                            window.location.reload()
                        })
                    })
                    .catch(error => {
                        swal({
                            title: "¡Atención!",
                            text: error.response.data,
                            icon: "error"
                        })
                    })
                }
            })
        },
        deleteSoap(){
            const urlParams = new URLSearchParams(window.location.search);
            this.soapID = urlParams.get("id");

            swal({
                title: "Confirmación",
                text: "¿Está seguro de querer eliminar este producto?",
                icon: "warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.delete(`/api/admin/soaps/${this.soapID}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                    .then(response => {
                        swal({
                            title: "Jabón eliminado",
                            icon: "success",
                            button: true
                        })
                        .then(response =>{
                            window.location.replace("manager-products.html")
                        })
                    })
                }
            })
        },

        /* DATA SHAMPOO */

        loadShampoos(){
            axios.get("/api/admin/shampoos")
            .then(response => {
                this.listShampoos = response.data

                this.orderByID(this.listShampoos)
            })
        },
        loadShampooID(){
            const urlParams = new URLSearchParams(window.location.search);
            this.shampooID = urlParams.get("id");

            axios.get(`/api/admin/shampoos/${this.shampooID}`)
            .then(response => {
                this.shampoo.code = response.data.code
                this.shampoo.name = response.data.name
                this.shampoo.description = response.data.description
                this.shampoo.url = response.data.url
                this.shampoo.price = response.data.price
                this.shampoo.stock = response.data.stock
            })
        },
        addShampoo(){
            swal({
                title: "Confirmación",
                text: "¿Está seguro de querer registrar este producto?",
                icon: "warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.post("/api/admin/shampoos",{
                        name: this.shampoo.name,
                        description: this.shampoo.description,
                        url: this.shampoo.url,
                        price: this.shampoo.price,
                        stock: this.shampoo.stock
                    })
                    .then(response => {
                        swal({
                            title: "Datos guardados",
                            icon: "success",
                            button: true
                        })
                        .then(response =>{
                            window.location.reload()
                        })
                    })
                    .catch(error => {
                        swal({
                            title: "¡Atención!",
                            text: error.response.data,
                            icon: "error"
                        })
                    })
                }
            })
        },
        changeDataShampoo(){
            const urlParams = new URLSearchParams(window.location.search);
            this.shampooID = urlParams.get("id");

            swal({
                title: "Confirmación",
                text: "¿Está seguro de querer guardar los nuevos datos?",
                icon: "warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.put(`/api/admin/shampoos/${this.shampooID}`,{
                        name: this.shampoo.name,
                        description: this.shampoo.description,
                        url: this.shampoo.url,
                        price: this.shampoo.price,
                        stock: this.shampoo.stock
                    })
                    .then(response => {
                        swal({
                            title: "Datos guardados",
                            icon: "success",
                            button: true
                        })
                        .then(response =>{
                            window.location.reload()
                        })
                    })
                    .catch(error => {
                        swal({
                            title: "¡Atención!",
                            text: error.response.data,
                            icon: "error"
                        })
                    })
                }
            })
        },
        deleteShampoo(){
            const urlParams = new URLSearchParams(window.location.search);
            this.shampooID = urlParams.get("id");

            swal({
                title: "Confirmación",
                text: "¿Está seguro de querer eliminar este producto?",
                icon: "warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.delete(`/api/admin/shampoos/${this.shampooID}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                    .then(response => {
                        swal({
                            title: "Champú eliminado",
                            icon: "success",
                            button: true
                        })
                        .then(response =>{
                            window.location.replace("manager-products.html")
                        })
                    })
                }
            })
        },

        /* DATA CONDITIONER */

        loadConditioners(){
            axios.get("/api/admin/conditioners")
            .then(response => {
                this.listConditioners = response.data

                this.orderByID(this.listConditioners)
            })
        },
        loadConditionerID(){
            const urlParams = new URLSearchParams(window.location.search);
            this.conditionerID = urlParams.get("id");

            axios.get(`/api/admin/conditioners/${this.conditionerID}`)
            .then(response => {
                this.conditioner.code = response.data.code
                this.conditioner.name = response.data.name
                this.conditioner.description = response.data.description
                this.conditioner.url = response.data.url
                this.conditioner.price = response.data.price
                this.conditioner.stock = response.data.stock
            })
        },
        addConditioner(){
            swal({
                title: "Confirmación",
                text: "¿Está seguro de querer registrar este producto?",
                icon: "warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.post("/api/admin/conditioners",{
                        name: this.conditioner.name,
                        description: this.conditioner.description,
                        url: this.conditioner.url,
                        price: this.conditioner.price,
                        stock: this.conditioner.stock
                    })
                    .then(response => {
                        swal({
                            title: "Datos guardados",
                            icon: "success",
                            button: true
                        })
                        .then(response =>{
                            window.location.reload()
                        })
                    })
                    .catch(error => {
                        swal({
                            title: "¡Atención!",
                            text: error.response.data,
                            icon: "error"
                        })
                    })
                }
            })
        },
        changeDataConditioner(){
            const urlParams = new URLSearchParams(window.location.search);
            this.conditionerID = urlParams.get("id");

            swal({
                title: "Confirmación",
                text: "¿Está seguro de querer guardar los nuevos datos?",
                icon: "warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.put(`/api/admin/conditioners/${this.conditionerID}`,{
                        name: this.conditioner.name,
                        description: this.conditioner.description,
                        url: this.conditioner.url,
                        price: this.conditioner.price,
                        stock: this.conditioner.stock
                    })
                    .then(response => {
                        swal({
                            title: "Datos guardados",
                            icon: "success",
                            button: true
                        })
                        .then(response =>{
                            window.location.reload()
                        })
                    })
                    .catch(error => {
                        swal({
                            title: "¡Atención!",
                            text: error.response.data,
                            icon: "error"
                        })
                    })
                }
            })
        },
        deleteConditioner(){
            const urlParams = new URLSearchParams(window.location.search);
            this.conditionerID = urlParams.get("id");

            swal({
                title: "Confirmación",
                text: "¿Está seguro de querer eliminar este producto?",
                icon: "warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.delete(`/api/admin/conditioners/${this.conditionerID}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                    .then(response => {
                        swal({
                            title: "Acondicionador eliminado",
                            icon: "success",
                            button: true
                        })
                        .then(response =>{
                            window.location.replace("manager-products.html")
                        })
                    })
                }
            })
        },

        /* DATA PACKS */
        
        /* FUNCTIONS */
        momentFooter(date){
            return moment(date).format("YYYY")
        },
        showPassword(){
            if(this.type === "password"){
                this.type = "text"
                this.hidden = false
                this.show = true
            }else{
                this.type = "password"
                this.hidden = true
                this.show = false
            }
        },
        orderByID(array){
            return array.sort((a, b) => {
                if(a.id < b.id){
                    return -1
                }else if(a.id > b.id){
                    return 1
                }else{
                    return 0
                }
            })
        },
        formatPrice(data){
            return numeral(data).format("0,0.00")
        }
    },
    computed:{
        /* BÚSQUEDAS */
        searchClients(){
            this.filterClient = this.listClients.filter(client => client.code.toLowerCase().includes(this.searchClient.toLowerCase()));

            this.noSearchClient;
            return this.filterClient
        },
        noSearchClient(){
            if(this.filterClient.length === 0){
                return this.noClient = true
            }else{
                return this.noClient = false
            }
        },
        searchSoaps(){
            this.filterSoap = this.listSoaps.filter(soap => soap.code.toLowerCase().includes(this.searchSoap.toLowerCase()));

            this.noSearchSoap;
            return this.filterSoap
        },
        noSearchSoap(){
            if(this.filterSoap.length === 0){
                return this.noSoap = true
            }else{
                return this.noSoap = false
            }
        },
        searchShampoos(){
            this.filterShampoo = this.listShampoos.filter(shampoo => shampoo.code.toLowerCase().includes(this.searchShampoo.toLowerCase()));

            this.noSearchShampoo;
            return this.filterShampoo
        },
        noSearchShampoo(){
            if(this.filterShampoo.length === 0){
                return this.noShampoo = true
            }else{
                return this.noShampoo = false
            }
        },
        searchConditioners(){
            this.filterConditioner = this.listConditioners.filter(book => book.code.toLowerCase().includes(this.searchConditioner.toLowerCase()));

            this.noSearchConditioner;
            return this.filterConditioner
        },
        noSearchConditioner(){
            if(this.filterConditioner.length === 0){
                return this.noConditioner = true
            }else{
                return this.noConditioner = false
            }
        },

        /* CONTADORES */
        counterSoap(){
            return this.lengthText + this.soap.description.length
        },
        counterShampoo(){
            return this.lengthText + this.shampoo.description.length
        },
        counterConditioner(){
            return this.lengthText + this.conditioner.description.length
        }
    }
})
const consola = app.mount("#app")