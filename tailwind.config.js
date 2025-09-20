/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/main/resources/**/*.{html,js}",    // adjust if you use JS files with Tailwind classes
  ],
  theme: {
    extend: {},
  },
  plugins: [],
  darkMode: "selector",
}
