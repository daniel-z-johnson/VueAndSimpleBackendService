<template>
  <div class="user-list">
    <h2>Users List</h2>
    <div v-if="loading" class="loading">Loading...</div>
    <div v-if="error" class="error">{{ error }}</div>
    <div v-if="!loading && users.length === 0" class="empty">No users found</div>
    
    <div v-for="user in users" :key="user.id" class="user-card">
      <div class="user-info">
        <h3>{{ user.username }}</h3>
        <p class="user-id">ID: {{ user.id }}</p>
      </div>
      
      <div class="hobbies-section">
        <h4>Hobbies:</h4>
        <ul v-if="user.hobbies && user.hobbies.length > 0" class="hobbies-list">
          <li v-for="hobby in user.hobbies" :key="hobby.id" class="hobby-item">
            {{ hobby.name }}
          </li>
        </ul>
        <p v-else class="no-hobbies">No hobbies yet</p>
      </div>
      
      <div class="add-hobby-form">
        <input
          v-model="newHobbies[user.id]"
          type="text"
          placeholder="Add hobby"
          @keyup.enter="addHobby(user.id)"
        />
        <button @click="addHobby(user.id)">Add Hobby</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { userService } from '../services/userService'

const users = ref([])
const loading = ref(false)
const error = ref(null)
const newHobbies = ref({})

const fetchUsers = async () => {
  loading.value = true
  error.value = null
  try {
    users.value = await userService.getAllUsers()
  } catch (err) {
    error.value = 'Failed to load users: ' + err.message
  } finally {
    loading.value = false
  }
}

const addHobby = async (userId) => {
  const hobbyName = newHobbies.value[userId]
  if (!hobbyName || !hobbyName.trim()) {
    return
  }
  
  try {
    await userService.addHobbyToUser(userId, hobbyName.trim())
    newHobbies.value[userId] = ''
    await fetchUsers()
  } catch (err) {
    error.value = 'Failed to add hobby: ' + err.message
  }
}

onMounted(fetchUsers)
</script>

<style scoped>
.user-list {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

h2 {
  color: #2c3e50;
  margin-bottom: 20px;
}

.loading,
.error,
.empty {
  text-align: center;
  padding: 20px;
  font-size: 18px;
}

.error {
  color: #e74c3c;
}

.user-card {
  background: white;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.user-info h3 {
  margin: 0 0 5px 0;
  color: #2c3e50;
}

.user-id {
  margin: 0;
  color: #7f8c8d;
  font-size: 14px;
}

.hobbies-section {
  margin-top: 15px;
}

.hobbies-section h4 {
  margin: 0 0 10px 0;
  color: #34495e;
}

.hobbies-list {
  list-style: none;
  padding: 0;
  margin: 0;
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.hobby-item {
  background: #3498db;
  color: white;
  padding: 5px 12px;
  border-radius: 15px;
  font-size: 14px;
}

.no-hobbies {
  color: #95a5a6;
  font-style: italic;
  margin: 0;
}

.add-hobby-form {
  margin-top: 15px;
  display: flex;
  gap: 10px;
}

.add-hobby-form input {
  flex: 1;
  padding: 8px 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
}

.add-hobby-form button {
  padding: 8px 16px;
  background: #27ae60;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
}

.add-hobby-form button:hover {
  background: #229954;
}
</style>
