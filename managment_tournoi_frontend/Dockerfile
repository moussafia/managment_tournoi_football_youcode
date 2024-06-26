# Use the official Node.js image with LTS version as a base image
FROM node:latest as build

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json to leverage Docker cache
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code
COPY . .

# Build the Angular application
RUN npm run build --prod

# Use a lightweight Nginx image to serve the Angular app
FROM nginx:alpine

# Copy the built Angular app from the previous stage to the Nginx web root
COPY --from=build /app/dist/* /usr/share/nginx/html/

COPY nginx.conf /etc/nginx/conf.d/default.conf


# Expose the port used by Nginx (usually 80 by default)
EXPOSE 80

# Nginx runs in the foreground when started
CMD ["nginx", "-g", "daemon off;"]