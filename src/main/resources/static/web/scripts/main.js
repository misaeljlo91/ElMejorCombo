const app = Vue.createApp({
    data(){
        return{
            listClient: [],
            listPacks: [],
            filterPack: [],
            searchPack: "",
            noPack: false
        }
    },
    created(){
        this.loadClient()
        this.loadPacks()
    },
    methods:{
        loadClient(){
            axios.get("/api/clients/current")
            .then(response => {
                this.listClient = response.data
            })
        },
        loadPacks(){
            axios.get("/api/admin/packs") //CAMBIAR
            .then(response => {
                this.listPacks = response.data

                this.orderByID(this.listPacks)
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
                        window.location.reload()
                    })
                }
            })
        },
        formatPrice(data){
            return numeral(data).format("0,0.00")
        },
        momentFooter(date){
            return moment(date).format("YYYY")
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
        orderByName(array, order){
            if(order == "A-Z"){
                return array.sort((a, b) => {
                    if(a.namePack < b.namePack){
                        return -1
                    }else if(a.namePack > b.namePack){
                        return 1
                    }
                })
            }

            if(order == "Z-A"){
                return array.sort((a, b) => {
                    if(a.namePack > b.namePack){
                        return -1
                    }else if(a.namePack < b.namePack){
                        return 1
                    }
                })
            }
        },
        orderByPrice(array, order){
            if(order == "UP"){
                return array.sort((a, b) => {
                    if(a.price < b.price){
                        return -1
                    }else if(a.price > b.price){
                        return 1
                    }
                })
            }

            if(order == "DOWN"){
                return array.sort((a, b) => {
                    if(a.price > b.price){
                        return -1
                    }else if(a.price < b.price){
                        return 1
                    }
                })
            }
        },
    },
    computed:{
        searchPacks(){
            this.filterPack = this.listPacks.filter(pack => pack.code.toLowerCase().includes(this.searchPack.toLowerCase()));

            this.noSearchPack;
            return this.filterPack
        },
        noSearchPack(){
            if(this.filterPack.length === 0){
                return this.noPack = true
            }else{
                return this.noPack = false
            }
        }
    }
})
app.mount("#app")