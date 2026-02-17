const API_BASE_URL = 'http://localhost:8080'

export const userService = {
  async getAllUsers() {
    const response = await fetch(`${API_BASE_URL}/users`)
    if (!response.ok) {
      throw new Error('Failed to fetch users')
    }
    return await response.json()
  },

  async createUser(username) {
    const response = await fetch(`${API_BASE_URL}/users`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ username }),
    })
    if (!response.ok) {
      throw new Error('Failed to create user')
    }
    return await response.json()
  },

  async addHobbyToUser(userId, hobbyName) {
    const response = await fetch(`${API_BASE_URL}/users/${userId}/hobbies/${hobbyName}`, {
      method: 'POST',
    })
    if (!response.ok) {
      throw new Error('Failed to add hobby')
    }
    return await response.json()
  },
}
