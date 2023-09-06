<template>
    <div class="main">
        <header>
            <h1>Welcome to your dashboard</h1>
        </header>

        <section class="subtitle">
            Request a voucher
        </section>

        <section class="brand-header">
            <h2>What brands would you like to work with</h2>

            <!-- search bar -->
            <div class="search-bar">
                <input type="text" placeholder="Search for a brand">
            </div>

            <div class="brand-carousal">
                <h3>brands</h3>
            </div>


        </section>

        <section class="product-header">
            <h2>What product would you like to promote</h2>

            <!-- search bar -->
            <div class="search-bar">
                <input type="text" placeholder="Search for a brand">
            </div>

            <div class="brand-carousal">
                <h3>brands</h3>
            </div>





        </section>

        <button class="submit-btn">
            Submit
        </button>






    </div>
</template>

<style scoped lang="scss">
.main {
    background-color: #e6fcff;
    display: flex;
    flex-direction: column;
    align-items: center;
    color: #0b2c5c;
    // justify-content: center;
}

.subtitle {
    // color: white;
    font-size: 1.5em;
    margin: 10px 0;
}

input {
    flex: 1;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

header {
    // color: white;
    font-size: 2em;
    margin: 10px 0;
}



.submit-btn {
    background-color: #007bff;
    // color: #fff;
    font-size: 1.2em;
    border-radius: 5px;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s;
    justify-content: center;
    display: flex;
}


</style>


