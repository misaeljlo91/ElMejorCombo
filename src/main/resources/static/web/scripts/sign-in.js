const app = Vue.createApp({
    data(){
        return{
            data:{
                firstname: "",
                lastname: "",
                username: "",
                email: "",
                password: "",
                password2: "",
                recover: ""
            },
            error: false,
            type: "password", hidden: true, show: false,
            type2: "password", hidden2: true, show2: false,
            type3: "password", hidden3: true, show3: false
        }
    },
    created(){
        
    },
    methods:{
        signIn(){
            axios.post("/api/login",`username=${this.data.username}&password=${this.data.password}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
                window.location.replace("./webPages/index.html")
            })
            .catch(error => {
                swal({
                    title: "¡Atención!",
                    text: "Usuario o contraseña incorrecta",
                    icon: "error"
                })
                .then(confrimation => {
                    this.data.username = ""
                    this.data.password = ""
                })
            })
        },
        momentFooter(date){
            return moment(date).format("YYYY")
        },
        registerUser(){
            if(this.data.password === this.data.password2){
                axios.post("/api/clients",{
                    firstname: this.data.firstname,
                    lastname: this.data.lastname,
                    username: this.data.username,
                    email: this.data.email,
                    password: this.data.password
                })
                .then(response => {
                    swal({
                        title: "¡Registro exitoso!",
                        text: "Bienvenido(a) a nuestra plataforma " + this.data.firstname + " " + this.data.lastname,
                        icon: "success",
                        button: true
                    })
                    .then(confirmation => {
                        axios.post("/api/login",`username=${this.data.username}&password=${this.data.password}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                        .then(response => {
                            window.location.replace("./webPages/index.html")
                        })
                    })
                })
                .catch(error => {
                    swal({
                        text: error.response.data,
                        icon: "error"
                    })
                })
            }else{
                swal({
                    text: "Las contraseñas ingresadas no coinciden.",
                    icon: "error"
                })
            }
        },
        forgotData(){
            axios.post("/api/forgot-data",`email=${this.data.email}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
                swal({
                    title: "¡Genial!",
                    text: response.data,
                    icon: "success"
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
        },
        forgotUsername(){
            axios.patch("/api/clients/forgot-username",`email=${this.data.email}&username=${this.data.username}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
                swal({
                    title: "Cambio de usuario exitoso",
                    icon: "success",
                    button: true
                })
                .then(confirmation => {
                    window.location.replace("sign-in.html")
                })
            })
            .catch(error => {
                swal({
                    text: error.response.data,
                    icon: "error"
                })
            })
        },
        forgotPassword(){
            if(this.data.password === this.data.password2){
                axios.patch("/api/clients/forgot-password",`email=${this.data.email}&password=${this.data.password}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                .then(response => {
                    swal({
                        title: "Cambio de contraseña exitoso",
                        icon: "success",
                        button: true
                    })
                    .then(confirmation => {
                        window.location.replace("sign-in.html")
                    })
                })
                .catch(error => {
                    swal({
                        text: error.response.data,
                        icon: "error"
                    })
                })
            }else{
                swal({
                    text: "Las contraseñas ingresadas no coinciden.",
                    icon: "error"
                })
            }
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
        showPassword2(){
            if(this.type2 === "password"){
                this.type2 = "text"
                this.hidden2 = false
                this.show2 = true
            }else{
                this.type2 = "password"
                this.hidden2 = true
                this.show2 = false
            }
        },
        showPassword3(){
            if(this.type3 === "password"){
                this.type3 = "text"
                this.hidden3 = false
                this.show3 = true
            }else{
                this.type3 = "password"
                this.hidden3 = true
                this.show3 = false
            }
        }
    },
    computed:{

    }
})
app.mount("#app")