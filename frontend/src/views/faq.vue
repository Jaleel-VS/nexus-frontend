<template>
  <div class="faq-page">
    <div class="wrapper">
        <div class="logo">
            <img src="../assets/project_logo.png" alt="logo">       
        </div>
      <h1>Frequently Asked Questions</h1>
  
      <div class="faq" v-for="(question, index) in faqList" :key="index">
        <button class="accordion" @click="toggleAccordion(index)" :aria-expanded="question.active">
          {{ question.question }}
        </button>
        <div class="panel" :class="{ active: question.active }">
          <p>{{ question.answer }}</p>
        </div>
      </div>
    </div>
  </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        faqList: [
          {
            question: 'What is Blockchain?',
            answer: 'Blockchain is a type of shared database that stores information in blocks which are connected in a chain',
            active: false,
          },
          {
            question: 'Who is Impact.com?',
            answer: 'Impact is a global leader in partnership automation and they offer businesses a comprehensive set of software tools to manage their partner marketing activities.',
            active: false,
          },
          {
            question: 'How do I sign up as an influencer?',
            answer: 'Simply follow the application process on our home page.',
            active: false,
          },
          {
            question: 'What are smart contracts?',
            answer: 'Smart contracts are programs stored on a blockchain that exxecutes automatically when specific conditions are met.',
            active: false,
          },
          {
            question: 'What brands do you work with?',
            answer: 'Nike, Adidas and Samsung',
            active: false,
          },
        ],
      };
    },
    methods: {
      toggleAccordion(index) {
        this.faqList[index].active = !this.faqList[index].active;
      },
    },
  };
  </script>
  
  <style scoped>
  @import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");

.faq-page {
  background-image: url(../assets/color-bars.svg);
  background-size: cover;
  background-position: center;
  backdrop-filter: blur(0px);
  background-color: #001f3f;
  height: 150vh;
  width: 100vw;
}

.faq-page::before {
    content: "";
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 1, 63, 0.85); /* Adjust the opacity as needed */
    z-index: -1; /* Ensure the semi-transparent pane is behind other elements */
}
.wrapper {
  max-width: 75%;
  margin: auto;
}
.logo {
  text-align: center;
  margin-top: 1rem; /* Adjust the margin as needed */
}
.logo img {
  max-width: 300px; /* Adjust the max-width of the logo as needed */
  vertical-align: middle;
  display: inline-block;
}


.wrapper > p,
.wrapper > h1 {
  margin: 1.5rem 0;
  text-align: center;
  font-size: 40px;
  color: #E040FB;
  font-family: 'Poppins', serif;
}
.wrapper > h1 {
  letter-spacing: 3px;
}
.accordion {
  background-color: white;
  color: rgba(0, 0, 0, 0.8);
  cursor: pointer;
  font-size: 1.2rem;
  width: 100%;
  padding: 2rem 2.5rem;
  border: none;
  outline: none;
  transition: 0.4s;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-weight: bold;
}
.accordion i {
  font-size: 1.6rem;
}
.active,
.accordion:hover {
  background-color: #f1f7f5;
}
.panel {
  padding: 0 2rem 2.5rem 2rem;
  background-color: white;
  overflow: hidden;
  background-color: #f1f7f5;
  display: none;
}
.panel.active {
  display: block; /* Show the answer when active */
}
.panel p {
  color: rgba(0, 0, 0, 0.7);
  font-size: 1.2rem;
  line-height: 1.4;
}
.faq {
  border: 1px solid rgba(0, 0, 0, 0.2);
  margin: 10px 0;
}
.faq.active {
  border: none;
}
</style>
  