const app = Vue.createApp({
    data(){
        return{
            listClient: []
        }
    },
    created(){
        this.loadClient()
    },
    methods:{
        loadClient(){
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
                        window.location.replace("index.html")
                    })
                }
            })
        },
        momentFooter(date){
            return moment(date).format("YYYY")
        }
    },
    computed:{

    }
})
app.mount("#app")