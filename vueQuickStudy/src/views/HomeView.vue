<template>
  <main>
    <div class="home">
      <h2>Welcome to User Management System</h2>
      <p class="intro">This application allows you to manage users and their hobbies.</p>
      
      <div class="features">
        <div class="feature-card">
          <h3>👥 View Users</h3>
          <p>Browse all users and their hobbies</p>
          <RouterLink to="/users" class="btn">Go to Users</RouterLink>
        </div>
        
        <div class="feature-card">
          <h3>➕ Create User</h3>
          <p>Add a new user to the system</p>
          <RouterLink to="/create-user" class="btn">Create New User</RouterLink>
        </div>
      </div>
      
      <div class="api-info">
        <h3>Backend API Endpoints</h3>
        <ul>
          <li><code>GET /users</code> - Get all users with their hobbies</li>
          <li><code>POST /users</code> - Create a new user</li>
          <li><code>POST /users/{'{userId}'}/hobbies/{'{hobbyName}'}</code> - Add hobby to user</li>
        </ul>
      </div>
    </div>
  </main>
</template>

<script setup>
import { RouterLink } from 'vue-router'
</script>

<style scoped>
.home {
  max-width: 800px;
  margin: 0 auto;
}

h2 {
  color: #2c3e50;
  font-size: 32px;
  margin-bottom: 10px;
}

.intro {
  font-size: 18px;
  color: #7f8c8d;
  margin-bottom: 40px;
}

.features {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  margin-bottom: 40px;
}

.feature-card {
  background: white;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.feature-card h3 {
  color: #2c3e50;
  margin-bottom: 10px;
}

.feature-card p {
  color: #7f8c8d;
  margin-bottom: 20px;
}

.btn {
  display: inline-block;
  padding: 10px 20px;
  background: #3498db;
  color: white;
  text-decoration: none;
  border-radius: 4px;
  transition: background 0.3s;
}

.btn:hover {
  background: #2980b9;
}

.api-info {
  background: white;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.api-info h3 {
  color: #2c3e50;
  margin-bottom: 15px;
}

.api-info ul {
  list-style: none;
  padding: 0;
}

.api-info li {
  padding: 10px;
  margin-bottom: 5px;
  background: #f8f9fa;
  border-radius: 4px;
}

code {
  background: #2c3e50;
  color: #ecf0f1;
  padding: 2px 6px;
  border-radius: 3px;
  font-family: 'Courier New', monospace;
}
</style>
