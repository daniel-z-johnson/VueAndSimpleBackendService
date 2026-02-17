<template>
  <div class="create-user">
    <h2>Create New User</h2>
    <form @submit.prevent="handleSubmit">
      <div class="form-group">
        <label for="username">Username:</label>
        <input
          id="username"
          v-model="username"
          type="text"
          placeholder="Enter username"
          required
        />
      </div>
      
      <div v-if="error" class="error-message">{{ error }}</div>
      <div v-if="successMessage" class="success-message">{{ successMessage }}</div>
      
      <button type="submit" :disabled="loading">
        {{ loading ? 'Creating...' : 'Create User' }}
      </button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { userService } from '../services/userService'
import { useRouter } from 'vue-router'

const router = useRouter()
const username = ref('')
const loading = ref(false)
const error = ref(null)
const successMessage = ref(null)

const handleSubmit = async () => {
  if (!username.value.trim()) {
    error.value = 'Username is required'
    return
  }
  
  loading.value = true
  error.value = null
  successMessage.value = null
  
  try {
    await userService.createUser(username.value.trim())
    successMessage.value = 'User created successfully!'
    username.value = ''
    
    setTimeout(() => {
      router.push('/users')
    }, 1500)
  } catch (err) {
    error.value = 'Failed to create user: ' + err.message
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.create-user {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
}

h2 {
  color: #2c3e50;
  margin-bottom: 20px;
}

form {
  background: white;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 8px;
  color: #34495e;
  font-weight: 500;
}

input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 16px;
  box-sizing: border-box;
}

input:focus {
  outline: none;
  border-color: #3498db;
}

button {
  width: 100%;
  padding: 12px;
  background: #3498db;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  transition: background 0.3s;
}

button:hover:not(:disabled) {
  background: #2980b9;
}

button:disabled {
  background: #95a5a6;
  cursor: not-allowed;
}

.error-message {
  color: #e74c3c;
  padding: 10px;
  background: #fadbd8;
  border-radius: 4px;
  margin-bottom: 15px;
}

.success-message {
  color: #27ae60;
  padding: 10px;
  background: #d5f4e6;
  border-radius: 4px;
  margin-bottom: 15px;
}
</style>
